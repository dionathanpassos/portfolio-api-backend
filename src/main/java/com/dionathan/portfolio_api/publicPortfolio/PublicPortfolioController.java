package com.dionathan.portfolio_api.publicPortfolio;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/public/users")
public class PublicPortfolioController {

    private final PublicPortfolioService publicPortfolioService;

    @GetMapping("/{username}/portfolio")
    public ResponseEntity<PortfolioResponseDTO> getPortfolio(@PathVariable String username) {
        return ResponseEntity.ok(publicPortfolioService.getPortfolio(username));

    }
}
