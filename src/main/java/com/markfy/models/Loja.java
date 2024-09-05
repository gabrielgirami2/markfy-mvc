package com.markfy.models;

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
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "loja")
    private List<Cliente> clientes;

    @OneToMany(mappedBy = "loja")
    private List<Produto> produtos;

    public Loja(CadastroLojaDTO cadastroLojaDTO) {
        this.nome = cadastroLojaDTO.nome();
        this.cnpj = cadastroLojaDTO.cnpj();
    }
}
