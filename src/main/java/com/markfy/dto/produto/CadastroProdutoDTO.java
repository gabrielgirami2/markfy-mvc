package com.markfy.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroProdutoDTO(
        @NotBlank
        String nomeProduto,
        @NotNull
        Float valorProduto,
        @NotBlank
        String marcaProduto,
        @NotBlank
        String tamanhoProduto,
        @NotNull
        Integer estoque

) {
}
