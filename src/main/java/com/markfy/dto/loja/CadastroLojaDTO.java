package com.markfy.dto.loja;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record CadastroLojaDTO(
        @NotBlank
        String nome,
        @NotBlank
                @CNPJ
        String cnpj
) {
}
