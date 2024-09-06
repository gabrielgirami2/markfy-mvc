package com.markfy.dto.cliente;

import com.markfy.models.enums.EstadoCivilEnum;
import com.markfy.models.enums.NivelEducacionalEnum;
import com.markfy.models.enums.OcupacaoEnum;
import com.markfy.models.enums.SexoEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record AlterarClienteDTO(
        String nomeCliente,
        String sobrenomeCliente,
        @Past
        LocalDate dataDeNasciemto,
        @Email
        String emailCliente,
        SexoEnum sexo,
        @CPF
        String cpf,
        EstadoCivilEnum estadoCivil,
        NivelEducacionalEnum nivelEducacional,
        Float rendaAnual,
        OcupacaoEnum ocupacao,
        Long idLoja
) {
}
