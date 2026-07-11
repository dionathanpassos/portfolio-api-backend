package com.dionathan.portfolio_api.social.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SocialRequestDTO(

        @NotBlank(message = "URl GitHub é obrigatório")
        @Pattern(
                regexp = "^https://(www\\.)?github\\.com/[A-Za-z0-9_.-]+/?$",
                message = "Informe uma URL válida do GitHub."
        )
        String githubUrl,

        @NotBlank(message = "URl LinkedIn é obrigatório")
        @Pattern(
                regexp = "^https://(www\\.)?linkedin\\.com/in/[A-Za-z0-9-_%]+/?$",
                message = "Informe uma URL válida do LinkedIn."
        )
        String linkedinUrl,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Informe um email válido")
        String email,

        String website

) {
}
