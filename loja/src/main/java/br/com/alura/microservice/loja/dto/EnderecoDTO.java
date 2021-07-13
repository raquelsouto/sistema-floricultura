package br.com.alura.microservice.loja.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnderecoDTO {

    private String rua;
    private int numero;
    private String estado;

}
