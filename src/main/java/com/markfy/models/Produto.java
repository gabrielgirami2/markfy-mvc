package com.markfy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.markfy.dto.produto.AlterarProdutoDTO;
import com.markfy.dto.produto.CadastroProdutoDTO;
import com.markfy.dto.usuario.CadastroUsuarioDTO;
import com.markfy.models.enums.TamanhoEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;
    private String nome;
    private Float valor;
    private String marca;
    private TamanhoEnum tamanho;
    private Integer estoque;
    @ManyToOne
    @JoinColumn(name = "loja_id")
    @JsonIgnore
    private Loja loja;

    public Produto(String nome, Float valor, String marca, TamanhoEnum tamanho, Integer estoque) {
        this.nome = nome;
        this.valor = valor;
        this.marca = marca;
        this.tamanho = tamanho;
        this.estoque = estoque;
    }

    public Produto(CadastroProdutoDTO cadastroProdutoDTO, Loja loja) {
        this.nome = cadastroProdutoDTO.nomeProduto();
        this.valor = cadastroProdutoDTO.valorProduto();
        this.marca = cadastroProdutoDTO.marcaProduto();
        this.tamanho = TamanhoEnum.valueOf(cadastroProdutoDTO.tamanhoProduto());
        this.estoque = cadastroProdutoDTO.estoque();
        this.loja = loja;
    }

    public void alterar(AlterarProdutoDTO alterarProdutoDTO) {
        if(alterarProdutoDTO.nomeProduto() != null) this.nome = alterarProdutoDTO.nomeProduto();
        if(alterarProdutoDTO.valorProduto() != null) this.valor = alterarProdutoDTO.valorProduto();
        if(alterarProdutoDTO.marcaProduto() != null) this.marca = alterarProdutoDTO.marcaProduto();
        if(alterarProdutoDTO.tamanhoProduto() != null) this.tamanho = TamanhoEnum.valueOf(alterarProdutoDTO.tamanhoProduto());
        if(alterarProdutoDTO.estoque() != null) this.estoque = alterarProdutoDTO.estoque();
    }
}
