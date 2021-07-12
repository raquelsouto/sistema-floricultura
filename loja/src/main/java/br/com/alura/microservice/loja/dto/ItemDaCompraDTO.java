package br.com.alura.microservice.loja.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class ItemDaCompraDTO {

    private Long id;
    private int quantidade;
}
