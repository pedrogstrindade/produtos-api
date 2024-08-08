package com.produtos.api.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.api.dto.ProdutoDTO;
import com.produtos.api.model.Produto;
import com.produtos.api.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.cadastrarProduto(produto));
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> listarProdutos(Pageable paginacao) {
        return ResponseEntity.ok().body(produtoService.listarProdutosDTO(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> acharProdutoPeloId(@PathVariable("id") Long id) {
        ProdutoDTO produto = produtoService.acharProdutoPeloId(id);

        if (Objects.isNull(produto)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(produto);
    }

    @GetMapping("/busca/{nome}")
    public ResponseEntity<Page<ProdutoDTO>> acharProdutoLetraPorLetra(@PathVariable String nome, Pageable paginacao) {
        return ResponseEntity.ok().body(produtoService.acharProdutosPeloNome(nome, paginacao));
    }

    @GetMapping("/filtro")
    public ResponseEntity<Page<ProdutoDTO>> filtrarProdutoPorPrecos(@RequestParam Double precoMin, @RequestParam Double precoMax, Pageable paginacao) {
        Page<ProdutoDTO> produtos = produtoService.acharProdutosEntreOsPrecos(precoMin, precoMax, paginacao);

        if (Objects.isNull(produtos)) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok().body(produtos);
    
    }

    @GetMapping("/esgotados")
    public ResponseEntity<List<Produto>> acharProdutosEsgotados() {
        return ResponseEntity.ok().body(produtoService.verificarProdutosEsgotados());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        ProdutoDTO produto = produtoService.acharProdutoPeloId(id);

        if (Objects.isNull(produto)) {
            return ResponseEntity.notFound().build();
        }

        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto dadosProduto) {
        ProdutoDTO produto = produtoService.acharProdutoPeloId(id);

        if (Objects.isNull(produto)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(produtoService.atualizarProduto(id, dadosProduto));
    }
}
