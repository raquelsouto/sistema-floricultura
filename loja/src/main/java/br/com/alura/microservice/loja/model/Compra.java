package br.com.alura.microservice.loja.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Compra {

    @Id
    private Long pedidoId;
    private Integer tempoDepreparo;
    private String enderecoDestino;

}
