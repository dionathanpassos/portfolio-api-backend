package com.dionathan.portfolio_api.social;

import com.dionathan.portfolio_api.social.dto.SocialRequestDTO;
import com.dionathan.portfolio_api.social.dto.SocialResponseDTO;
import com.dionathan.portfolio_api.social.dto.SocialUpdateRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/social")
public class SocialController {

    private final SocialService socialService;

    @Operation(summary = "Cria social")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Social criado"),
            @ApiResponse(responseCode = "400", description = "Campos inválidos"),
    })
    @PostMapping
    public ResponseEntity<SocialResponseDTO> create(@Valid @RequestBody SocialRequestDTO requestDTO) {
        SocialResponseDTO created = socialService.create(requestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Operation(summary = "Atualiza social por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Social atualizada"),
            @ApiResponse(responseCode = "404", description = "Social não encontrada"),
    })
    @PatchMapping("/{id}")
    public ResponseEntity<SocialResponseDTO> update(@Valid @RequestBody SocialUpdateRequestDTO requestDTO, @PathVariable Long id) {
        SocialResponseDTO updated = socialService.update(requestDTO, id);

        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Buscar social por usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Social encontrado"),
            @ApiResponse(responseCode = "404", description = "Social não encontrado")
    })
    @GetMapping
    public ResponseEntity<SocialResponseDTO> getSocial() {
        SocialResponseDTO social = socialService.findByUser();

        return ResponseEntity.ok(social);
    }
}
