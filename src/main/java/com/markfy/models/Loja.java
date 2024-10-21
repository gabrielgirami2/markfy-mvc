package com.markfy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.markfy.dto.loja.AlterarLojaDTO;
import com.markfy.dto.loja.CadastroLojaDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table
@Entity
@Data
@NoArgsConstructor
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLoja;
    private String nome;
    private String cnpj;

    @OneToMany(mappedBy = "loja")
    @JsonIgnore
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "loja")
    private List<Cliente> clientes;

    @OneToMany(mappedBy = "loja")
    private List<Produto> produtos;

    @Override
    public String toString() {
        return "Loja{" +
                "idLoja=" + idLoja +
                ", nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }

    public Loja(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public Loja(CadastroLojaDTO cadastroLojaDTO) {
        this.nome = cadastroLojaDTO.nome();
        this.cnpj = cadastroLojaDTO.cnpj();
    }


    public void alterar(AlterarLojaDTO alterarLojaDTO) {
        if(alterarLojaDTO.nome() != null) this.nome = alterarLojaDTO.nome();
        if(alterarLojaDTO.cnpj() != null) this.cnpj = alterarLojaDTO.cnpj();
    }
}
