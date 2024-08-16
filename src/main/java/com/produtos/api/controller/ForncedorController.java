package com.produtos.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.api.model.Fornecedor;
import com.produtos.api.service.FornecedorService;

@RestController
@RequestMapping("/fornecedores")
public class ForncedorController {
    
    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<Fornecedor> cadastrarFornecedor(@RequestBody Fornecedor fornecedor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.cadastrarFornecedor(fornecedor));
    }
}