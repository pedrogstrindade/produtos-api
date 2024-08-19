package com.produtos.api.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.produtos.api.constants.UsuarioStatus;
import com.produtos.api.dto.UsuarioDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NOME_USUARIO", nullable = false)
    private String nome;

    @Column(name = "CPF_USUARIO", nullable = false, unique = true)
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "NASCIMENTO_USUARIO", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "SENHA_USUARIO", nullable = false)
    private String senha;

    @Column(name = "EMAIL_USUARIO", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_USUARIO", nullable = false)
    private UsuarioStatus status;

    public UsuarioDTO toDTO() {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(id);
        dto.setNome(nome);
        dto.setEmail(email);
        dto.setStatus(status);
        dto.setIdade(ChronoUnit.YEARS.between(dataNascimento, LocalDate.now()));

        return dto;
    }
}