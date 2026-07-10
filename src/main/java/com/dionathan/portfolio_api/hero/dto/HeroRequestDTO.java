package com.dionathan.portfolio_api.hero.dto;

import jakarta.validation.constraints.NotBlank;

public record HeroRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "Posição é obrigatório")
        String position,

        @NotBlank(message = "Eyebrow é obrigatório")
        String eyebrow,

        @NotBlank(message = "Introdução é obrigatório")
        String introdution
) {
}
