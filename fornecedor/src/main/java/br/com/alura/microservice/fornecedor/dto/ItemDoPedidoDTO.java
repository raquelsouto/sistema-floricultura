package br.com.alura.microservice.fornecedor.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDoPedidoDTO {

	private long id;
	private int quantidade;
	public long getId() {
		return id;
	}

}
