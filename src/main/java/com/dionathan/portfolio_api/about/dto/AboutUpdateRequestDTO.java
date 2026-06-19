package com.dionathan.portfolio_api.about.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AboutUpdateRequestDTO(
        String name,
        String title,
        String paragraphOne,
        String paragraphTwo,
        String paragraphThree,
        String location,
        String githubUrl,
        String linkedinUrl,

        @Email(message = "Email inválido")
        String email

) {
}
