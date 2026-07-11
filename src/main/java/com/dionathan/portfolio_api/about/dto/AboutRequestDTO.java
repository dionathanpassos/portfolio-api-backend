package com.dionathan.portfolio_api.about.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AboutRequestDTO(

        @NotBlank(message = "Título é obrigatório")
        String title,

        @NotBlank(message = "o paráfrago é obrigatório")
        String paragraphOne,

        String paragraphTwo,
        String paragraphThree
) {
}
