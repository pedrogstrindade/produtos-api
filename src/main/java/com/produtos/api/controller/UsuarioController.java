package com.produtos.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> acharUsuarioPeloId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.acharUsuarioPeloId(id);

        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(usuario);
    }
}