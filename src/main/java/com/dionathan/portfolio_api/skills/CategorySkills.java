package com.dionathan.portfolio_api.skills;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Categorias disponíveis para skills"
)
public enum CategorySkills {
    BACKEND,
    FRONTEND,
    DEVOPS,
    DATABASE,
    TOOLS
}
