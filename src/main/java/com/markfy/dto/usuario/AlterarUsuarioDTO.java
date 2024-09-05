package com.markfy.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record AlterarUsuarioDTO(
        String nomeUsuario,
        String sobrenomeUsuario,
        String emailUsuario,
        String senha
) {
}
