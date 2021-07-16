package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.client.TransportadorClient;
import br.com.alura.microservice.loja.model.CompraState;
import br.com.alura.microservice.loja.repository.CompraRepository;
import br.com.alura.microservice.loja.dto.*;
import br.com.alura.microservice.loja.model.Compra;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompraService {

    private static final Logger LOG =  LoggerFactory.getLogger(CompraService.class);

    @Autowired
    FornecedorClient fornecedorClient;

    @Autowired
    TransportadorClient transportadorClient;

    @Autowired
    CompraRepository compraRepository;

    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    public Compra getById(Long id) {
        return compraRepository.findById(id).orElse(new Compra());
    }

    public Compra reprocessaCompra(Long id) {
        return null;
    }

    public Compra cancelaCompra(Long id) {
        return null;
    }

    @HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
    public Compra realizaCompra(CompraDTO compraDTO) {

        Compra compraSalva = new Compra();
        compraSalva.setState(CompraState.RECEBIDO);
        compraSalva.setEnderecoDestino(compraDTO.getEndereco().toString());
        compraRepository.save(compraSalva);
        compraDTO.setCompraId(compraSalva.getId());

        InfoFornecedorDTO  info = fornecedorClient.getInfoPorEstado(compraDTO.getEndereco().getEstado());
        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compraDTO.getItens());
        compraSalva.setState(CompraState.PEDIDO_REALIZADO);
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDepreparo(pedido.getTempoDePreparo());
        compraRepository.save(compraSalva);

        //Informações para reservar uma entrega
        InfoEntregaDTO entregaDto = new InfoEntregaDTO();
        entregaDto.setPedidoId(pedido.getId());
        entregaDto.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
        entregaDto.setEnderecoOrigem(info.getEndereco());
        entregaDto.setEnderecoDestino(compraDTO.getEndereco().toString());
        VoucherDTO voucherDTO = transportadorClient.reservaEntrega(entregaDto);
        compraSalva.setState(CompraState.RESERVA_ENTREGA_REALIZADA);
        compraSalva.setDataParaEntrega(voucherDTO.getPrevisaoParaEntrega());
        compraSalva.setVoucher(voucherDTO.getNumero());
        compraRepository.save(compraSalva);

        return compraSalva;
    }

    public Compra realizaCompraFallback(CompraDTO compraDTO) {
        if(compraDTO.getCompraId() != null) {
            return compraRepository.findById(compraDTO.getCompraId()).get();
        }
        Compra compraFallback = new Compra();
        compraFallback.setEnderecoDestino(compraDTO.getEndereco().toString());
        return compraFallback;
    }
}
