package com.dionathan.portfolio_api.project.dto;

import com.dionathan.portfolio_api.project.ProjectStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Schema(description = "Dados para criação do projeto")
public record ProjectRequestDTO(

        @Schema(
                description = "Nome do projeto",
                example = "Autenticação com JWT"
        )
        @NotBlank(message = "Título obrigatório")
        String title,

        @Schema(
                description = "Breve descrição do projeto",
                example = "API para cadastro e login de usuários"
        )
        @NotBlank(message = "Título obrigatório")
        String description,

        @Schema(
                description = "Texto amigável gerado automaticamente a partir do título para compor a URL do portfólio",
                example = "e-commerce-com-spring-boot-e-java"
        )
        @NotBlank(message = "Título obrigatório")
        String slug,

        @Schema(
                description = "URL do repositório GitHub",
                example = "https://github.com/meu-projeto"
        )
        @NotBlank(message = "Título obrigatório")
        String repositoryUrl,

        @Schema(
                description = "URL da aplicação de demonstração",
                example = "https://meu-site.com.br"
        )
        String demoUrl,

        @Schema(
                description = "Ids das skills utilizadas no projeto",
                example = "[1,2,4]"
        )
        Set<Long> skillsIds


) {
}
