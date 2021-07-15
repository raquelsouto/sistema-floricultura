package br.com.alura.microservice.transportador.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entrega {


	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	private Long pedidoId;
	private LocalDate dataParaBusca;
	private LocalDate previsaoParaEntrega;
	private String enderecoOrigem;
	private String enderecoDestino;

}
