package com.dionathan.portfolio_api.timeline.dto;

import com.dionathan.portfolio_api.timeline.TimelineType;

import java.time.LocalDate;

public record TimelineResponseDTO(
        Long id,
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
