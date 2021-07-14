package br.com.alura.microservice.loja.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoEntregaDTO {

        private Long pedidoId;
        private LocalDate dataParaEntrega;
        private String enderecoOrigem;
        private String enderecoDestino;

}
