package com.dionathan.portfolio_api.project.dto;

import com.dionathan.portfolio_api.project.ProjectStatus;
import com.dionathan.portfolio_api.skills.Skill;
import com.dionathan.portfolio_api.skills.dto.SkillResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Set;

public record ProjectResponseDTO(

        @Schema(
                description = "Identificador da skill",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Nome do projeto",
                example = "Autenticação com JWT"
        )
        String title,

        @Schema(
                description = "Breve descrição do projeto",
                example = "API para cadastro e login de usuários"
        )
        String description,

        @Schema(
                description = "Texto amigável gerado automaticamente a partir do título para compor a URL do portfólio",
                example = "e-commerce-com-spring-boot-e-java"
        )
        String slug,

        @Schema(
                description = "URL do repositório GitHub",
                example = "https://github.com/meu-projeto"
        )
        String repositoryUrl,

        @Schema(
                description = "URL da aplicação de demonstração",
                example = "https://meu-site.com.br"
        )
        String demoUrl,

        @Schema(
                description = "Se o projeto está em destaque",
                example = "true"
        )
        boolean featured,

        @Schema(
                description = "Estágio em que se enconta o desenvolvimento do projeto",
                example = "IN_PROGRESS"
        )
        ProjectStatus status,

        @Schema(
                description = "Data e hora da criação do projeto",
                example = "2026-06-19 23:58:46"
        )
        LocalDateTime createdAt,

        @Schema(
                description = "Ids das skills utilizadas no projeto",
                example = "[1,2,4]"
        )
        Set<SkillResponseDTO> skills


) {
}
