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

public record CadastroClienteDTO(
        @NotBlank
        String nomeCliente,
        @NotBlank
        String sobrenomeCliente,
        @NotNull
                @Past
        LocalDate dataDeNasciemto,
        @NotBlank
            @Email
        String emailCliente,
        @NotNull
        SexoEnum sexo,
        @NotBlank
                @CPF
        String cpf,
        @NotNull
        EstadoCivilEnum estadoCivil,
        @NotNull
        NivelEducacionalEnum nivelEducacional,
        @NotNull
        Float rendaAnual,
        @NotNull
        OcupacaoEnum ocupacao
) {
}
