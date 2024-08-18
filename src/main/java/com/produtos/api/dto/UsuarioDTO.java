package com.produtos.api.dto;

import com.produtos.api.constants.UsuarioStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    
    private Long id;
    private String nome;
    private long idade;
    private String email;
    private UsuarioStatus status;
    
}