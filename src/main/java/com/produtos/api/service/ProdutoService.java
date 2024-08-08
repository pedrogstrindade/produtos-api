package com.produtos.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.produtos.api.constants.ProdutoStatus;
import com.produtos.api.dto.ProdutoDTO;
import com.produtos.api.model.Produto;
import com.produtos.api.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
    
    public ProdutoDTO acharProdutoPeloId(Long id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if (produtoOpt.isPresent()) {
            return produtoOpt.get().toDTO();
        }

        return null;
    }
    
    public Page<ProdutoDTO> listarProdutosDTO(Pageable paginacao) {
        return produtoRepository.findAll(paginacao).map(produto -> produto.toDTO());
    }

    public Page<ProdutoDTO> acharProdutosPeloNome(String nome, Pageable paginacao) {
        Page<Produto> produtos = produtoRepository.findByNomeLike(nome + "%", paginacao);
        List<ProdutoDTO> produtosDTO = produtos.stream().map(Produto::toDTO).collect(Collectors.toList());

        return new PageImpl<>(produtosDTO, paginacao, produtos.getTotalElements());

    }

    public Page<ProdutoDTO> acharProdutosEntreOsPrecos(Double precoMin, Double precoMax, Pageable paginacao) {
        Page<Produto> produtos = produtoRepository.findByPrecoBetween(precoMin, precoMax, paginacao);
        
        List<ProdutoDTO> produtosDTO = produtos.getContent().stream().map(Produto::toDTO).toList();
        
        return new PageImpl<>(produtosDTO, paginacao, produtos.getTotalElements());
        
    }

    public List<Produto> verificarProdutosEsgotados(ProdutoStatus status) {
        return produtoRepository.findByStatus(ProdutoStatus.ESGOTADO);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto atualizarProduto(Long id, Produto dadosProduto) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();

            produto.setNome(dadosProduto.getNome());
            produto.setPreco(dadosProduto.getPreco());
            produto.setMarca(dadosProduto.getMarca());
            produto.setDescricao(dadosProduto.getDescricao());
            produto.setQuantidade(dadosProduto.getQuantidade());
            produto.setDataVencimento(dadosProduto.getDataVencimento());
            produto.setDataFabricacao(dadosProduto.getDataFabricacao());
            produto.setStatus(dadosProduto.getStatus());

            return produtoRepository.save(produto);
        }
    
        return null;
    }
}
