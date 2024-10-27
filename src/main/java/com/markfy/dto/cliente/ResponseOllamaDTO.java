package com.markfy.dto.cliente;

public record ResponseOllamaDTO(
        String nome,
        String sobrenome,
        String email,
        String cpf
) {
}
