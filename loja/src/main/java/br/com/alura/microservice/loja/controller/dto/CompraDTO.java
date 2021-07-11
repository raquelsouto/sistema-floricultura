package br.com.alura.microservice.loja.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompraDTO {

    private List<ItemDaCompra> itens;
    private EnderecoDTO endereco;

}
