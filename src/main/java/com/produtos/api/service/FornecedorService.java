package com.produtos.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.produtos.api.model.Fornecedor;
import com.produtos.api.repository.FornecedorRepository;

@Service
public class FornecedorService {
    
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor cadastrarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Page<Fornecedor> listarFornecedores(Pageable paginacao) {
        return fornecedorRepository.findAll(paginacao);
    }

    public Fornecedor acharFornecedorPeloId(Long id) {
        Optional<Fornecedor> fornecedorOpt = fornecedorRepository.findById(id);

        if (fornecedorOpt.isPresent()) {
            return fornecedorOpt.get();
        }

        return null;
    }

    public void deletarFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }

    public Fornecedor atualizarFornecedor(Long id, Fornecedor dadosFornecedor) {
        Optional<Fornecedor> fornecedorOpt = fornecedorRepository.findById(id);

        if (fornecedorOpt.isPresent()) {
            Fornecedor fornecedor = fornecedorOpt.get();

            fornecedor.setNome(dadosFornecedor.getNome());
            fornecedor.setCnpj(dadosFornecedor.getCnpj());

            return fornecedorRepository.save(fornecedor);
        }
    
        return null;
    }
}
