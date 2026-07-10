package com.dionathan.portfolio_api.publicPortfolio;

import com.dionathan.portfolio_api.about.dto.AboutResponseDTO;
import com.dionathan.portfolio_api.hero.dto.HeroResponseDTO;
import com.dionathan.portfolio_api.project.dto.ProjectResponseDTO;
import com.dionathan.portfolio_api.skills.dto.SkillResponseDTO;
import com.dionathan.portfolio_api.timeline.dto.TimelineResponseDTO;

import java.util.List;

public record PortfolioResponseDTO(
        HeroResponseDTO hero,
        AboutResponseDTO about,
        List<SkillResponseDTO> skills,
        List<ProjectResponseDTO> projects,
        List<TimelineResponseDTO> timelines
) {
}
