package devkaik.TrabalhoFaculdade.controller;

import devkaik.TrabalhoFaculdade.dto.ReservaRequest;
import devkaik.TrabalhoFaculdade.dto.ReservaResponse;
import devkaik.TrabalhoFaculdade.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@Tag(name = "Reservas", description = "Gerenciamento das reservas do restaurante")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    @Operation(summary = "Listar reservas", description = "Retorna todas as reservas cadastradas")
    public ResponseEntity<List<ReservaResponse>> listarTodas() {
        return ResponseEntity.ok(reservaService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar reserva por id", description = "Retorna uma reserva pelo identificador")
    public ResponseEntity<ReservaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar reserva", description = "Cadastra uma nova reserva",
            responses = {@ApiResponse(responseCode = "201", description = "Reserva cadastrada com sucesso",
                    content = @Content(schema = @Schema(implementation = ReservaResponse.class)))}
    )
    public ResponseEntity<ReservaResponse> criar(@Valid @RequestBody ReservaRequest request) {
        ReservaResponse response = reservaService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar reserva", description = "Atualiza os dados de uma reserva existente")
    public ResponseEntity<ReservaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody ReservaRequest request) {
        return ResponseEntity.ok(reservaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover reserva", description = "Remove uma reserva do sistema",
            responses = {@ApiResponse(responseCode = "204", description = "Reserva removida com sucesso")}
    )
    public void remover(@PathVariable Long id) {
        reservaService.remover(id);
    }
}
