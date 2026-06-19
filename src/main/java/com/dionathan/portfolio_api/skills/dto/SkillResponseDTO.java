package com.dionathan.portfolio_api.skills.dto;

import com.dionathan.portfolio_api.skills.CategorySkills;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados de uma skill")
public record SkillResponseDTO(

        @Schema(
                description = "Identificador da skill",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nome da skill",
                example = "Java"
        )
        String name,

        @Schema(
                description = "Categoria da skill",
                example = "BACKEND"
        )
        CategorySkills categorySkills,

        @Schema(
                description = "Url do ícone da skill",
                example = "https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg"
        )
        String iconUrl,

        @Schema(
                description = "Nível da skill",
                example = "90"
        )
        int level,

        @Schema(
                description = "Indica se a skill está ativa",
                example = "true"
        )
        boolean active
) {
}
