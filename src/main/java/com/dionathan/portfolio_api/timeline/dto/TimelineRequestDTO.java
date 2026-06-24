package com.dionathan.portfolio_api.timeline.dto;

import com.dionathan.portfolio_api.timeline.TimelineType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Dados para criação do projeto")
public record TimelineRequestDTO(

        @Schema(
                description = "Tipo da experiência",
                example = "WORK, EDUCATION, PROJECT"
        )
        @NotNull(message = "Tipo é obrigatório")
        TimelineType type,

        @Schema(
                description = "Título da experiência",
                example = "Graduação em Sistemas de Informação"
        )
        @NotBlank(message = "Título é obrigatório")
        String title,

        @Schema(
                description = "Subtitulo da experiência",
                example = "Universidade Xyz"
        )
        @NotBlank(message = "Subtitulo é obrigatório")
        String subtitle,

        @Schema(
                description = "Descrição da experiência",
                example = "Final da formação acadêmica em tecnologia"
        )
        String description,

        @Schema(
                description = "Início da experiência",
                example = "2026-01-01"
        )
        @NotNull(message = "Início é obrigatório")
        LocalDate startDate,

        @Schema(
                description = "Término da experiência",
                example = "2026-12-01"
        )
        LocalDate endDate,

        @Schema(
                description = "Indica se a experiência está em andamento, por exemplo: se é o trabalho atual",
                example = "true"
        )
        Boolean current,

        @Schema(
                description = "Indica se a experiência será visualizada na timeline",
                example = "true"
        )
        Boolean featured
) {
}
