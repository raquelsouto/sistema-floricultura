package br.com.alura.microservice.loja.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoPedidoDTO {

    private Long id;
    private Integer tempoDePreparo;
}
