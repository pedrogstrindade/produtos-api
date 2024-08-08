package com.produtos.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    
    private Long id;
    private String nome;
    private Double preco;
    private String descricao;
    private String marca;
    private long vencimentoDias;

}
