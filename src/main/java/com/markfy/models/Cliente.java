package com.markfy.models;

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
}
