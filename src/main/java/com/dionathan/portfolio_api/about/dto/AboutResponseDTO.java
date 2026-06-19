package com.dionathan.portfolio_api.about.dto;

public record AboutResponseDTO(
        Long id,
        String name,
        String title,
        String bio,
        String location,
        String githubUrl,
        String linkedinUrl,
        String email
) {
}
