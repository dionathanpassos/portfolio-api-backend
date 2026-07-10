package com.dionathan.portfolio_api.hero;

import com.dionathan.portfolio_api.hero.dto.HeroRequestDTO;
import com.dionathan.portfolio_api.hero.dto.HeroResponseDTO;
import com.dionathan.portfolio_api.hero.dto.HeroUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hero")
public class HeroController {

    private final HeroService heroService;

    @PostMapping
    public ResponseEntity<HeroResponseDTO> create(@Valid @RequestBody HeroRequestDTO requestDTO) {
        HeroResponseDTO created = heroService.create(requestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HeroResponseDTO> update (@RequestBody HeroUpdateRequestDTO requestDTO, @PathVariable Long id) {
        HeroResponseDTO updated = heroService.update(requestDTO, id);

        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<HeroResponseDTO> getHero() {
        HeroResponseDTO hero = heroService.findByUser();

        return ResponseEntity.ok(hero);
    }
}
