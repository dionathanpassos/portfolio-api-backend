package com.dionathan.portfolio_api.project.dto;

import com.dionathan.portfolio_api.project.ProjectStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Schema(description = "Dados para atualização do projeto")
public record ProjectUpdateRequestDTO(

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
        Boolean featured,

        @Schema(
                description = "Estágio em que se enconta o desenvolvimento do projeto",
                example = "IN_PROGRESS"
        )
        ProjectStatus status,

        @Schema(
                description = "Ids das skills utilizadas no projeto",
                example = "[1,2,4]"
        )
        Set<Long> skillsIds


) {
}
