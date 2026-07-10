package com.dionathan.portfolio_api.hero.dto;

import jakarta.validation.constraints.NotBlank;

public record HeroResponseDTO(

        Long id,
        String name,
        String position,
        String eyebrow,
        String introdution
) {
}
