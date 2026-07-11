package com.dionathan.portfolio_api.social.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SocialUpdateRequestDTO(

        @Pattern(
                regexp = "^https://(www\\.)?github\\.com/[A-Za-z0-9_.-]+/?$",
                message = "Informe uma URL válida do GitHub."
        )
        String githubUrl,

        @Pattern(
                regexp = "^https://(www\\.)?linkedin\\.com/in/[A-Za-z0-9-_%]+/?$",
                message = "Informe uma URL válida do LinkedIn."
        )
        String linkedinUrl,

        @Email(message = "Informe um email válido")
        String email,

        String website

) {
}
