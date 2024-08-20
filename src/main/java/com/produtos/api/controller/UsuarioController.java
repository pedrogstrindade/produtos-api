package com.produtos.api.controller;

import java.time.LocalDate;
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

import com.produtos.api.constants.UsuarioStatus;
import com.produtos.api.dto.UsuarioDTO;
import com.produtos.api.model.Usuario;
import com.produtos.api.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> listarUsuarios(Pageable paginacao) {
        return ResponseEntity.ok().body(usuarioService.listarUsuarios(paginacao));
    }

    @GetMapping("/nomec/{nome}")
    public ResponseEntity<UsuarioDTO> acharUsuarioPeloNome(@PathVariable String nome) {
        UsuarioDTO usuario = usuarioService.acharUsuarioPeloNome(nome);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(usuario);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> acharUsuarioPeloId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.acharUsuarioPeloId(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioDTO> acharUsuarioPeloCpf(@PathVariable String cpf) {
        UsuarioDTO usuario = usuarioService.acharUsuarioPeloCpf(cpf);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/nascimento/{dataNascimento}")
    public ResponseEntity<UsuarioDTO> acharUsuarioPeloNascimento(@PathVariable LocalDate dataNascimento) {
        UsuarioDTO usuario = usuarioService.acharUsuarioPeloNascimento(dataNascimento);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(usuario);
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Page<UsuarioDTO>> listarUsuariosLetraPorLetra(@PathVariable String nome, Pageable paginacao) {
        return ResponseEntity.ok().body(usuarioService.listarUsuariosLetraPorLetra(nome, paginacao));
    }

    @GetMapping("/status")
    public ResponseEntity<Page<UsuarioDTO>> listarUsuariosPorStatus(@RequestParam(name = "status", defaultValue = "ATIVO") UsuarioStatus status, Pageable paginacao) {
        return ResponseEntity.ok().body(usuarioService.listarUsuariosPeloStatus(status, paginacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.acharUsuarioPeloId(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario dadosUsuario) {
        UsuarioDTO usuario = usuarioService.acharUsuarioPeloId(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(usuarioService.atualizarUsuario(id, dadosUsuario));
    }
}