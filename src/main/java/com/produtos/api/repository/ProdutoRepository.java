package com.produtos.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.produtos.api.constants.ProdutoStatus;
import com.produtos.api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Page<Produto> findByNomeLike(String nome, Pageable paginacao);

    public Page<Produto> findByPrecoBetween(Double precoMin, Double precoMax, Pageable paginacao);

    public List<Produto> findByStatus(ProdutoStatus status);
}
