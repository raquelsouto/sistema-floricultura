package br.com.alura.microservice.loja.service;

import br.com.alura.microservice.loja.dto.CompraDTO;
import br.com.alura.microservice.loja.dto.InfoFornecedorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CompraService {

    @Autowired
    private RestTemplate cliente;

    public void realizaCompra(CompraDTO compraDTO) {
        RestTemplate cliente = new RestTemplate();

        ResponseEntity<InfoFornecedorDTO> exchange = cliente.exchange("http://fornecedor/info/"+compraDTO.getEndereco().getEstado(),
                HttpMethod.GET, null, InfoFornecedorDTO.class);

        System.out.println(exchange.getBody().getEndereco());

    }
}
