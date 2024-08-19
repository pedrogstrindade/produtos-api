package com.produtos.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.produtos.api.constants.UsuarioStatus;
import com.produtos.api.dto.UsuarioDTO;
import com.produtos.api.model.Usuario;
import com.produtos.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Page<UsuarioDTO> listarUsuarios(Pageable paginacao) {
        return usuarioRepository.findAll(paginacao).map(Usuario::toDTO);
    }

    public UsuarioDTO acharUsuarioPeloId(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get().toDTO();
        }

        return null;
    }

    public UsuarioDTO acharUsuarioPeloNascimento(LocalDate dataNascimento) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByDataNascimento(dataNascimento);

        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get().toDTO();
        }

        return null;
    }
    
    public UsuarioDTO acharUsuarioPeloCpf(String cpf) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCpf(cpf);

        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get().toDTO();
        }

        return null;
    }

    public UsuarioDTO acharUsuarioPeloEmail(String email) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get().toDTO();
        }

        return null;
    }
    
    public UsuarioDTO acharUsuarioPeloNome(String nome) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNomeContains(nome);

        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get().toDTO();
        }

        return null;

    }
    public Page<UsuarioDTO> listarUsuariosLetraPorLetra(String nome, Pageable paginacao) {
        Page<Usuario> usuarios = usuarioRepository.findByNomeLike(nome + "%", paginacao);
        List<UsuarioDTO> dto = usuarios.stream().map(Usuario::toDTO).collect(Collectors.toList());

        return new PageImpl<>(dto, paginacao, usuarios.getTotalElements());
    }

    public Page<UsuarioDTO> listarUsuariosPeloStatus(UsuarioStatus status, Pageable paginacao) {
        return usuarioRepository.findByStatus(status, paginacao).map(Usuario::toDTO);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario atualizarUsuario(Long id, Usuario dadosUsuario) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            usuario.setNome(dadosUsuario.getNome());
            usuario.setCpf(dadosUsuario.getCpf());
            usuario.setDataNascimento(dadosUsuario.getDataNascimento());
            usuario.setEmail(dadosUsuario.getEmail());
            usuario.setSenha(dadosUsuario.getSenha());
            usuario.setStatus(dadosUsuario.getStatus());
            
            return usuarioRepository.save(usuario);
        }

        return null;
    }
}