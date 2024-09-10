package com.markfy.dto.loja;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

public record CadastroLojaUsuarioDTO(
        @NotBlank
        String nomeLoja,
        @NotBlank
                @CNPJ
        String cnpj,
        @NotBlank
        String nomeUsuario,
        @NotBlank
        String sobrenomeUsuario,
        @NotBlank
        String emailUsuario,
        @NotBlank
        String senha,
        @NotNull
        Long idLoja
) {
}
