package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.dto.CompraDTO;
import br.com.alura.microservice.loja.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.dto.InfoPedidoDTO;
import br.com.alura.microservice.loja.model.Compra;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompraService {

//    @Autowired
//    private RestTemplate cliente;

//    @Autowired
//    DiscoveryClient eurekaClient;

    private static Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    FornecedorClient fornecedorClient;


    public Compra realizaCompra(CompraDTO compraDTO) {
        final String estado = compraDTO.getEndereco().getEstado();

        LOG.info("buscando informações do fornecedor de {}", estado);
        InfoFornecedorDTO  info = fornecedorClient.getInfoPorEstado(compraDTO.getEndereco().getEstado());

        LOG.info("realizando um pedido");
        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compraDTO.getItens());

        //Retorno do pedido
        Compra compraSalva = new Compra();
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDepreparo(pedido.getTempoDePreparo());
        compraSalva.setEnderecoDestino(compraDTO.getEndereco().toString());

        System.out.println(info.getEndereco());

        return compraSalva;
    }
}

/*     UTILIZANDO O RIBBON:
       public void realizaCompra(CompraDTO compraDTO)
       ResponseEntity<InfoFornecedorDTO> exchange = cliente.exchange("http://fornecedor/info/"+compraDTO.getEndereco().getEstado(),
                HttpMethod.GET, null, InfoFornecedorDTO.class);

        //Pega as informações do eurekaClient, substitui o ID de fornecedor por IP e PORTA da instância e seleciona para onde a requisição será enviada
        eurekaClient.getInstances("fornecedor").stream()
                        .forEach(fornecedor -> {
                            System.out.println("localhost:"+fornecedor.getPort());
                        }) ;
*/
