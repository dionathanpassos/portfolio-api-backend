package com.dionathan.portfolio_api.timeline.dto;

import com.dionathan.portfolio_api.timeline.TimelineType;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TimelineUpdateRequestDTO(

        TimelineType type,
        String title,
        String subtitle,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Boolean current,
        Boolean featured
) {
}
