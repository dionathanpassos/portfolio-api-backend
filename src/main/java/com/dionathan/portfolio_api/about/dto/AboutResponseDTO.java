package com.dionathan.portfolio_api.about.dto;

public record AboutResponseDTO(
        Long id,
        String title,
        String paragraphOne,
        String paragraphTwo,
        String paragraphThree

) {
}
