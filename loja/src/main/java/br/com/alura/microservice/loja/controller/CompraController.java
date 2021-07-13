package br.com.alura.microservice.loja.controller;

import br.com.alura.microservice.loja.dto.CompraDTO;
import br.com.alura.microservice.loja.model.Compra;
import br.com.alura.microservice.loja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    CompraService compraService;

    @PostMapping
    public Compra realizaCompra(@RequestBody CompraDTO compraDTO) {
        return compraService.realizaCompra(compraDTO);
    }
}
