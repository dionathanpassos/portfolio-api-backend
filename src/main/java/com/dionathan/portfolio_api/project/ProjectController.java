package com.dionathan.portfolio_api.project;

import com.dionathan.portfolio_api.project.dto.ProjectRequestDTO;
import com.dionathan.portfolio_api.project.dto.ProjectResponseDTO;
import com.dionathan.portfolio_api.project.dto.ProjectUpdateRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
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

@Tag(name = "Projects")
@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "Cria projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Projeto criado"),
            @ApiResponse(responseCode = "400", description = "Projeto criado"),
            @ApiResponse(responseCode = "409", description = "Projeto já cadastrado"),
    })
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> create(@Valid @RequestBody ProjectRequestDTO requestDTO) {
        ProjectResponseDTO created = projectService.create(requestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Operation(summary = "Buscar projeto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto encontrado"),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> findById(@PathVariable Long id) {
        ProjectResponseDTO project = projectService.findById(id);

        return ResponseEntity.ok(project);
    }

    @Operation(summary = "Busca todas os projetos deleted = false com paginação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projetos econtradas")
    })
    @GetMapping
    public ResponseEntity<Page<ProjectResponseDTO>> findAll(Pageable pageable) {
        Page<ProjectResponseDTO> projects = projectService.findAll(pageable);

        return ResponseEntity.ok(projects) ;
    }

    @Operation(summary = "Atualiza projeto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Projeto atualizada"),
            @ApiResponse(responseCode = "404", description = "Skill não encontrada"),
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> update(@PathVariable Long id, @RequestBody ProjectUpdateRequestDTO requestDTO) {
        ProjectResponseDTO updated = projectService.update(id, requestDTO);

        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Desativa o projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Projeto deletada/SoftDelete"),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrada"),
            @ApiResponse(responseCode = "422", description = "Violação da regra de negócio"),
    })
    @PatchMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
