package com.dionathan.portfolio_api.social.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SocialResponseDTO(
        @Schema(
                description = "Identificador único do registro",
                example = "1"
        )
        Long id,

        @Schema(
                description = "URL do perfil na plataforma GitHub",
                example = "https://github.com"
        )
        String githubUrl,

        @Schema(
                description = "URL do perfil na plataforma LinkedIn",
                example = "https://linkedin.com"
        )
        String linkedinUrl,

        @Schema(
                description = "Endereço de e-mail de contato principal",
                example = "usuario@email.com"
        )
        String email,

        @Schema(
                description = "URL do site pessoal ou portfólio",
                example = "https://meusite.com"
        )
        String website
) {
}
