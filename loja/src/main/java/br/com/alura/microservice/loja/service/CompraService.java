package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CompraService {

    public void realizaCompra(CompraDTO compraDTO) {
        RestTemplate cliente = new RestTemplate();
        ResponseEntity<InfoFornecedorDTO> exchange = cliente.exchange("http://localhost:8081/info/" + compraDTO.getEndereco().getEstado(),
                        HttpMethod.GET, null, InfoFornecedorDTO.class);

        System.out.println(exchange.getBody().getEndereco());

    }
}
