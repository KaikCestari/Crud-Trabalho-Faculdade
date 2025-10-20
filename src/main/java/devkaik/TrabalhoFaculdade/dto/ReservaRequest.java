package devkaik.TrabalhoFaculdade.dto;

import devkaik.TrabalhoFaculdade.Model.Enums.ReservaStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaRequest(
        @Schema(description = "Nome completo do cliente", example = "Ana Souza")
        @NotBlank(message = "O nome do cliente é obrigatório.")
        @Size(max = 120, message = "O nome do cliente deve ter no máximo 120 caracteres.")
        String nomeCliente,

        @Schema(description = "E-mail de contato do cliente", example = "ana.souza@email.com")
        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Informe um e-mail válido.")
        String email,

        @Schema(description = "Telefone de contato do cliente", example = "11999999999")
        @NotBlank(message = "O telefone é obrigatório.")
        @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres.")
        String telefone,

        @Schema(description = "Data da reserva", example = "2024-12-20")
        @NotNull(message = "A data da reserva é obrigatória.")
        @FutureOrPresent(message = "A data da reserva não pode estar no passado.")
        LocalDate data,

        @Schema(description = "Horário da reserva", example = "19:30:00")
        @NotNull(message = "O horário da reserva é obrigatório.")
        LocalTime horario,

        @Schema(description = "Quantidade de pessoas na reserva", example = "2")
        @NotNull(message = "O número de pessoas é obrigatório.")
        @Min(value = 1, message = "A reserva deve ser para ao menos uma pessoa.")
        Integer numeroPessoas,

        @Schema(description = "Status atual da reserva", example = "AGENDADA")
        ReservaStatus status,

        @Schema(description = "Observações adicionais", example = "Aniversário surpresa")
        @Size(max = 255, message = "As observações devem ter no máximo 255 caracteres.")
        String observacoes,

        @Schema(description = "Identificador da mesa desejada", example = "1")
        @NotNull(message = "O identificador da mesa é obrigatório.")
        Long mesaId
) {}
