package com.dionathan.portfolio_api.social.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Dados para criação do social")
public record SocialRequestDTO(

        @Schema(
                description = "Url do perfil do github",
                example = "https://github.com/dionathanpassos"
        )
        @NotBlank(message = "URl GitHub é obrigatório")
        @Pattern(
                regexp = "^https://(www\\.)?github\\.com/[A-Za-z0-9_.-]+/?$",
                message = "Informe uma URL válida do GitHub."
        )
        String githubUrl,

        @Schema(
                description = "Url do perfil do linkedin",
                example = "https://www.linkedin.com/in/dionathanpassos/"
        )
        @NotBlank(message = "URl LinkedIn é obrigatório")
        @Pattern(
                regexp = "^https://(www\\.)?linkedin\\.com/in/[A-Za-z0-9-_%]+/?$",
                message = "Informe uma URL válida do LinkedIn."
        )
        String linkedinUrl,

        @Schema(
                description = "Endereço de email para contato",
                example = "seuemail@gmail.com"
        )
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Informe um email válido")
        String email,

        @Schema(
                description = "Endereço do website (portfolio)",
                example = "seuwebsite.com"
        )
        String website

) {
}
