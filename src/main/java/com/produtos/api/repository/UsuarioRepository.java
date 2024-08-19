package com.produtos.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produtos.api.constants.UsuarioStatus;
import com.produtos.api.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Page<Usuario> findByNomeLike(String nome, Pageable paginacao);

    public Optional<Usuario> findByCpf(String cpf);

    public Page<Usuario> findByStatus(UsuarioStatus status, Pageable paginacao);

    public Optional<Usuario> findByNomeContains(String nome);
}