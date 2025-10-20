package devkaik.TrabalhoFaculdade.controller;

import devkaik.TrabalhoFaculdade.dto.MesaRequest;
import devkaik.TrabalhoFaculdade.dto.MesaResponse;
import devkaik.TrabalhoFaculdade.service.MesaService;
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
@RequestMapping("/api/mesas")
@Tag(name = "Mesas", description = "Gerenciamento das mesas do restaurante")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping
    @Operation(summary = "Listar mesas", description = "Retorna todas as mesas cadastradas")
    public ResponseEntity<List<MesaResponse>> listarTodas() {
        return ResponseEntity.ok(mesaService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar mesa por id", description = "Retorna uma mesa pelo identificador")
    public ResponseEntity<MesaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mesaService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cadastrar mesa", description = "Cadastra uma nova mesa no sistema",
            responses = {@ApiResponse(responseCode = "201", description = "Mesa cadastrada com sucesso",
                    content = @Content(schema = @Schema(implementation = MesaResponse.class)))}
    )
    public ResponseEntity<MesaResponse> criar(@Valid @RequestBody MesaRequest request) {
        MesaResponse response = mesaService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar mesa", description = "Atualiza as informações de uma mesa")
    public ResponseEntity<MesaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody MesaRequest request) {
        return ResponseEntity.ok(mesaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover mesa", description = "Remove uma mesa do sistema",
            responses = {@ApiResponse(responseCode = "204", description = "Mesa removida com sucesso")}
    )
    public void remover(@PathVariable Long id) {
        mesaService.remover(id);
    }
}
