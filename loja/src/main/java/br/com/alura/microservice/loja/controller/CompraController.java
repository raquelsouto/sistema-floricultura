package br.com.alura.microservice.loja.controller;

import br.com.alura.microservice.loja.dto.CompraDTO;
import br.com.alura.microservice.loja.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    CompraService compraService;

    @PostMapping
    public void realizaCompra(@RequestBody CompraDTO compraDTO) {
        compraService.realizaCompra(compraDTO);
    }
}
