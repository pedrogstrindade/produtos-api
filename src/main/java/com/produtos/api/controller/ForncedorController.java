package com.produtos.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public ResponseEntity<Page<Fornecedor>> listarFornecedores(Pageable paginacao) {
        return ResponseEntity.ok().body(fornecedorService.listarFornecedores(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> acharFornecedorPeloId(@PathVariable("id") Long id) {
        Fornecedor fornecedor = fornecedorService.acharFornecedorPeloId(id);

        if (Objects.isNull(fornecedor)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(fornecedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFornecedor(@PathVariable("id") Long id) {
        Fornecedor fornecedor = fornecedorService.acharFornecedorPeloId(id);

        if (Objects.isNull(fornecedor)) {
            return ResponseEntity.notFound().build();
        }

        fornecedorService.deletarFornecedor(id);
        return ResponseEntity.noContent().build();
    }
}