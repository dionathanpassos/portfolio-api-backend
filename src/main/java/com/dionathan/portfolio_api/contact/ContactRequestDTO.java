package com.dionathan.portfolio_api.contact;

public record ContactRequestDTO(
        String name,
        String email,
        String message
) {
}
