package com.dionathan.portfolio_api.social.dto;

import jakarta.validation.constraints.NotBlank;

public record SocialResponseDTO(
        Long id,
        String githubUrl,
        String linkedinUrl,
        String email,
        String website
) {
}
