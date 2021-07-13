package br.com.alura.microservice.loja.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compra {

    private Long pedidoId;
    private Integer tempoDepreparo;
    private String enderecoDestino;

}
