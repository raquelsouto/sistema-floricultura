package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.dto.CompraDTO;
import br.com.alura.microservice.loja.dto.InfoFornecedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompraService {

    @Autowired
    private RestTemplate cliente;

    @Autowired
    DiscoveryClient eurekaClient;

    public void realizaCompra(CompraDTO compraDTO) {
        ResponseEntity<InfoFornecedorDTO> exchange = cliente.exchange("http://fornecedor/info/"+compraDTO.getEndereco().getEstado(),
                HttpMethod.GET, null, InfoFornecedorDTO.class);


        /*Pega as informações do eurekaClient, substitui o ID de fornecedor por IP e PORTA da instância e
        seleciona para onde a requisição será enviada*/
        eurekaClient.getInstances("fornecedor").stream()
                        .forEach(fornecedor -> {
                            System.out.println("localhost:"+fornecedor.getPort());
                        }) ;

        System.out.println(exchange.getBody().getEndereco());

    }
}
