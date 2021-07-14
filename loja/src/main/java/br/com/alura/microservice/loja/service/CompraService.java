package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.client.repository.CompraRepository;
import br.com.alura.microservice.loja.dto.CompraDTO;
import br.com.alura.microservice.loja.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.dto.InfoPedidoDTO;
import br.com.alura.microservice.loja.model.Compra;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

    private static Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    FornecedorClient fornecedorClient;

    @HystrixCommand(threadPoolKey = "getByIdThreadPool")
    public Compra getById(Long id) {
        return compraRepository.findById(id).orElse(new Compra());
    }

    @HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
    public Compra realizaCompra(CompraDTO compraDTO) {
        final String estado = compraDTO.getEndereco().getEstado();

        LOG.info("buscando informações do fornecedor de {}", estado);
        InfoFornecedorDTO  info = fornecedorClient.getInfoPorEstado(compraDTO.getEndereco().getEstado());

        LOG.info("realizando um pedido");
        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compraDTO.getItens());

        System.out.println(info.getEndereco());

        Compra compraSalva = new Compra();
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDepreparo(pedido.getTempoDePreparo());
        compraSalva.setEnderecoDestino(compraDTO.getEndereco().toString());
        compraRepository.save(compraSalva);

        return compraSalva;
    }

    public Compra realizaCompraFallback(CompraDTO compraDTO) {
        Compra compraFallback = new Compra();
        compraFallback.setEnderecoDestino(compraDTO.getEndereco().toString());
        return compraFallback;
    }
}
