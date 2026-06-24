package com.dionathan.portfolio_api.timeline;

import com.dionathan.portfolio_api.timeline.dto.TimelineRequestDTO;
import com.dionathan.portfolio_api.timeline.dto.TimelineResponseDTO;
import com.dionathan.portfolio_api.timeline.dto.TimelineUpdateRequestDTO;
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

@Tag(name = "Timeline")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/timeline")
public class TimelineController {

    private final TimelineService timelineService;

    @Operation(summary = "Cria timeline")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Timeline criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Timeline já cadastrado"),
    })
    @PostMapping
    public ResponseEntity<TimelineResponseDTO> create(@Valid @RequestBody TimelineRequestDTO requestDTO) {
        TimelineResponseDTO created = timelineService.create(requestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @Operation(summary = "Buscar timeline por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Timeline encontrado"),
            @ApiResponse(responseCode = "404", description = "Timeline não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TimelineResponseDTO> findById(@PathVariable Long id) {
        TimelineResponseDTO timeline = timelineService.findById(id);

        return ResponseEntity.ok(timeline);
    }

    @Operation(summary = "Busca todas as timeline com featured = true com paginação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Timeline econtradas")
    })
    @GetMapping
    public ResponseEntity<Page<TimelineResponseDTO>> findAll(Pageable pageable) {
        Page<TimelineResponseDTO> timelines = timelineService.findAll(pageable);

        return ResponseEntity.ok(timelines);
    }

    @Operation(summary = "Atualiza timeline por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Timeline atualizada"),
            @ApiResponse(responseCode = "404", description = "Timeline não encontrada"),
    })
    @PatchMapping("/{id}")
    public ResponseEntity<TimelineResponseDTO> update(
            @PathVariable Long id,
            @RequestBody TimelineUpdateRequestDTO requestDTO) {

        TimelineResponseDTO update = timelineService.update(id, requestDTO);

        return ResponseEntity.ok(update);
    }


}
