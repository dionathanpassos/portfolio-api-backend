package com.dionathan.portfolio_api.skills.dto;

import com.dionathan.portfolio_api.skills.CategorySkills;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para criação de uma skill")
public record SkillRequestDTO(

        @Schema(
                description = "Nome da skill",
                example = "Java"
        )
        @NotBlank(message = "Nome da stack obrigatória")
        String name,

        @Schema(
                description = "Categoria da skill",
                example = "DEVOPS"
        )
        @NotNull(message = "Nome da stack obrigatória")
        CategorySkills category,

        @Schema(
                description = "Url do ícone da skill",
                example = "https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg"
        )
        String iconUrl,

        @Schema(
                description = "Nível de conhecimento de 0 - 100",
                example = "75"
        )
        @NotNull(message = "Level obrigatório")
        Integer level
) {
}
