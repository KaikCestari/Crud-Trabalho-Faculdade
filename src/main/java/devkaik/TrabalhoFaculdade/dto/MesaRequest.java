package devkaik.TrabalhoFaculdade.dto;

import jakarta.validation.constraints.Min;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MesaRequest(
        @Schema(description = "Identificador único da mesa", example = "Mesa 01")
        @NotBlank(message = "O identificador da mesa é obrigatório.")
        @Size(max = 40, message = "O identificador deve ter no máximo 40 caracteres.")
        String identificador,

        @Schema(description = "Capacidade de pessoas que a mesa comporta", example = "4")
        @NotNull(message = "A capacidade da mesa é obrigatória.")
        @Min(value = 1, message = "A capacidade mínima da mesa é 1 pessoa.")
        Integer capacidade
) {}
