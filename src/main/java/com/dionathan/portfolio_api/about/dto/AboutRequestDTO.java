package com.dionathan.portfolio_api.about.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AboutRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        String name,

        @NotBlank(message = "Título é obrigatório")
        String title,

        @NotBlank(message = "o paráfrago é obrigatório")
        String paragraphOne,

        String paragraphTwo,
        String paragraphThree,

        String location,

        @NotBlank(message = "GitHub URL é obrigatório")
        String githubUrl,

        @NotBlank(message = "LinkedIn URL é obrigatório")
        String linkedinUrl,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email

) {
}
