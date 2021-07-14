package br.com.alura.microservice.loja.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    private Integer tempoDepreparo;
    private String enderecoDestino;
    private LocalDate DataParaEntrega;

    @Enumerated(EnumType.STRING)
    private CompraState state;
    private Long voucher;

}
