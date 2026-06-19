package com.dionathan.portfolio_api.about;

import com.dionathan.portfolio_api.about.dto.AboutRequestDTO;
import com.dionathan.portfolio_api.about.dto.AboutResponseDTO;
import com.dionathan.portfolio_api.about.dto.AboutUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/about")
public class AboutController {

    private final AboutService aboutService;

    @PostMapping
    public ResponseEntity<AboutResponseDTO> create(@Valid @RequestBody AboutRequestDTO requestDTO) {
        AboutResponseDTO created = aboutService.create(requestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);

    }

    @PatchMapping
    public ResponseEntity<AboutResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AboutUpdateRequestDTO requestDTO) {
        AboutResponseDTO updated = aboutService.update(requestDTO, id);

        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<AboutResponseDTO> getAbout() {
        AboutResponseDTO about = aboutService.findByUser();

        return ResponseEntity.ok(about);
    }
}
