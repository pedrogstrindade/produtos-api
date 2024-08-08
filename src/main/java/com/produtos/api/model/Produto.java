package com.produtos.api.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.produtos.api.constants.ProdutoStatus;
import com.produtos.api.dto.ProdutoDTO;

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
@Entity(name = "tb_produtos")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Long id;
    
    @Column(name = "NOME_PRODUTO", nullable = false)
    private String nome;
    
    @Column(name = "DESCRICAO_PRODUTO", columnDefinition = "TEXT")
    private String descricao;
    
    @Column(name = "MARCA_PRODUTO", nullable = false)
    private String marca;

    @Column(name = "QUANTIDADE_PRODUTO", nullable = false)
    private int quantidade;
    
    @Column(name = "PRECO_PRODUTO", nullable = false)
    private Double preco;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DATA_FABRICACAO_PRODUTO")
    private LocalDate dataFabricacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DATA_VENCIMENTO_PRODUTO", nullable = false)
    private LocalDate dataVencimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_PRODUTO", nullable = false)
    private ProdutoStatus status;

    public ProdutoDTO toDTO() {

        ProdutoDTO dto = new ProdutoDTO();
        
        dto.setId(id);
        dto.setNome(nome);
        dto.setPreco(preco);
        dto.setMarca(marca);
        dto.setDescricao(descricao);
        
        long vencimentoDias = ChronoUnit.DAYS.between(LocalDate.now(), dataVencimento);
        dto.setVencimentoDias(vencimentoDias);

        return dto;
        
    }
}
