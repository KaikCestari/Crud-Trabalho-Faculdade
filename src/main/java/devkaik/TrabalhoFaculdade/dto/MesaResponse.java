package devkaik.TrabalhoFaculdade.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record MesaResponse(
        @Schema(description = "Identificador interno da mesa", example = "1")
        Long id,
        @Schema(description = "Identificador público da mesa", example = "Mesa 01")
        String identificador,
        @Schema(description = "Capacidade máxima da mesa", example = "4")
        Integer capacidade
) {}
