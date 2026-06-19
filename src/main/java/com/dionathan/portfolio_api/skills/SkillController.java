package com.dionathan.portfolio_api.skills;

import com.dionathan.portfolio_api.skills.dto.SkillRequestDTO;
import com.dionathan.portfolio_api.skills.dto.SkillResponseDTO;
import com.dionathan.portfolio_api.skills.dto.SkillUpdateRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "Skills")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/skills")
public class SkillController {

    private final SkillService skillService;

    @Operation(summary = "Cria skill")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Skill criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Skill name já cadastrada")
    })
    @PostMapping
    public ResponseEntity<SkillResponseDTO> create(@Valid @RequestBody SkillRequestDTO requestDTO) {
        SkillResponseDTO created = skillService.create(requestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Operation(summary = "Buscar skill por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Skill encontrada"),
            @ApiResponse(responseCode = "404", description = "Skill não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SkillResponseDTO> findById(@PathVariable Long id) {
        SkillResponseDTO skill = skillService.findById(id);

        return ResponseEntity.ok(skill);
    }

    @Operation(summary = "Busca todas as skill deleted = false com paginação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Skills econtradas")
    })
    @GetMapping
    public ResponseEntity<Page<SkillResponseDTO>> findAll(Pageable pageable) {
        Page<SkillResponseDTO> skills = skillService.findAll(pageable);

        return ResponseEntity.ok(skills);
    }

    @Operation(summary = "Atualiza skill por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Skill atualizada"),
            @ApiResponse(responseCode = "404", description = "Skill não encontrada"),
            @ApiResponse(responseCode = "422", description = "Violação da regra de negócio"),
    })

    @PatchMapping("/{id}")
    public ResponseEntity<SkillResponseDTO> update(
            @Parameter(description = "ID da skill", example = "1")
            @PathVariable Long id, @RequestBody SkillUpdateRequestDTO requestDTO)
    {
        SkillResponseDTO updated = skillService.update(id, requestDTO);

        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Desativa uma skill")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Skill desativada"),
            @ApiResponse(responseCode = "404", description = "Skill não encontrada"),
            @ApiResponse(responseCode = "422", description = "Violação da regra de negócio"),
    })
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<SkillResponseDTO> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.deactivate(id));
    }

    @Operation(summary = "Ativa uma skill")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Skill ativada"),
            @ApiResponse(responseCode = "404", description = "Skill não encontrada"),
            @ApiResponse(responseCode = "422", description = "Violação da regra de negócio"),
    })
    @PatchMapping("/{id}/activate")
    public ResponseEntity<SkillResponseDTO> activate(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.activate(id));
    }

    @Operation(summary = "Desativa a skill")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Skill deletada/SoftDelete"),
            @ApiResponse(responseCode = "404", description = "Skill não encontrada"),
            @ApiResponse(responseCode = "422", description = "Violação da regra de negócio"),
    })
    @PatchMapping("/{id}/delete")
    public ResponseEntity<SkillResponseDTO> delete(@PathVariable Long id) {
        skillService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
