package com.markfy.models;

import com.markfy.dto.cliente.AlterarClienteDTO;
import com.markfy.dto.cliente.CadastroClienteDTO;
import com.markfy.models.enums.EstadoCivilEnum;
import com.markfy.models.enums.NivelEducacionalEnum;
import com.markfy.models.enums.OcupacaoEnum;
import com.markfy.models.enums.SexoEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table
@Entity
@Data
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;
    private String nomeCliente;
    private String sobrenomeCliente;
    private LocalDate dataDeNasciemto;
    private String emailCliente;
    private SexoEnum sexo;
    private String cpf;
    private EstadoCivilEnum estadoCivil;
    private NivelEducacionalEnum nivelEducacional;
    private Float rendaAnual;
    private OcupacaoEnum ocupacao;
    @ManyToOne
    private Loja loja;

    public Cliente(String nomeCliente, String sobrenomeCliente, String emailCliente, LocalDate dataDeNasciemto, SexoEnum sexo, String cpf, EstadoCivilEnum estadoCivil, NivelEducacionalEnum nivelEducacional, Float rendaAnual, OcupacaoEnum ocupacao) {
        this.nomeCliente = nomeCliente;
        this.sobrenomeCliente = sobrenomeCliente;
        this.emailCliente = emailCliente;
        this.dataDeNasciemto = dataDeNasciemto;
        this.sexo = sexo;
        this.cpf = cpf;
        this.estadoCivil = estadoCivil;
        this.nivelEducacional = nivelEducacional;
        this.rendaAnual = rendaAnual;
        this.ocupacao = ocupacao;
    }

    public Cliente(CadastroClienteDTO cadastroClienteDTO, Loja loja) {
        this.nomeCliente = cadastroClienteDTO.nomeCliente();
        this.sobrenomeCliente = cadastroClienteDTO.sobrenomeCliente();
        this.dataDeNasciemto = cadastroClienteDTO.dataDeNasciemto();
        this.emailCliente = cadastroClienteDTO.emailCliente();
        this.sexo = cadastroClienteDTO.sexo();
        this.cpf = cadastroClienteDTO.cpf();
        this.estadoCivil = cadastroClienteDTO.estadoCivil();
        this.nivelEducacional = cadastroClienteDTO.nivelEducacional();
        this.rendaAnual = cadastroClienteDTO.rendaAnual();
        this.ocupacao = cadastroClienteDTO.ocupacao();
        this.loja = loja;
    }

    public void alterar(AlterarClienteDTO alterarClienteDTO) {
        if(alterarClienteDTO.nomeCliente() != null ) this.nomeCliente = alterarClienteDTO.nomeCliente();
        if(alterarClienteDTO.sobrenomeCliente() != null ) this.sobrenomeCliente = alterarClienteDTO.sobrenomeCliente();
        if(alterarClienteDTO.dataDeNasciemto() != null ) this.dataDeNasciemto = alterarClienteDTO.dataDeNasciemto();
        if(alterarClienteDTO.emailCliente() != null ) this.emailCliente = alterarClienteDTO.emailCliente();
        if(alterarClienteDTO.sexo() != null ) this.sexo = alterarClienteDTO.sexo();
        if(alterarClienteDTO.cpf() != null ) this.cpf = alterarClienteDTO.cpf();
        if(alterarClienteDTO.estadoCivil() != null ) this.estadoCivil = alterarClienteDTO.estadoCivil();
        if(alterarClienteDTO.nivelEducacional() != null ) this.nivelEducacional = alterarClienteDTO.nivelEducacional();
        if(alterarClienteDTO.rendaAnual() != null ) this.rendaAnual = alterarClienteDTO.rendaAnual();
        if(alterarClienteDTO.ocupacao() != null ) this.ocupacao = alterarClienteDTO.ocupacao();
    }
}
