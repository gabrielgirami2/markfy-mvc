package com.markfy.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlterarProdutoDTO(
        String nomeProduto,
        Float valorProduto,
        String marcaProduto,
        String tamanhoProduto,
        Integer estoque
) {
}
