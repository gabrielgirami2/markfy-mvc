package com.markfy.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroUsuarioDTO(
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
