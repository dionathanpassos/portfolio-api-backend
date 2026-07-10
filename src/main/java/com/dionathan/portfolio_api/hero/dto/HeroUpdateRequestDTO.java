package com.dionathan.portfolio_api.hero.dto;

import jakarta.validation.constraints.NotBlank;

public record HeroUpdateRequestDTO(


        String name,
        String position,
        String eyebrow,
        String introdution
) {
}
