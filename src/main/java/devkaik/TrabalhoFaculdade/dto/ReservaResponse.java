package devkaik.TrabalhoFaculdade.dto;

import devkaik.TrabalhoFaculdade.Model.Enums.ReservaStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaResponse(
        @Schema(description = "Identificador interno da reserva", example = "10")
        Long id,
        @Schema(description = "Nome completo do cliente", example = "Ana Souza")
        String nomeCliente,
        @Schema(description = "E-mail de contato do cliente", example = "ana.souza@email.com")
        String email,
        @Schema(description = "Telefone do cliente", example = "11999999999")
        String telefone,
        @Schema(description = "Data da reserva", example = "2024-12-20")
        LocalDate data,
        @Schema(description = "Horário da reserva", example = "19:30:00")
        LocalTime horario,
        @Schema(description = "Quantidade de pessoas incluídas na reserva", example = "2")
        Integer numeroPessoas,
        @Schema(description = "Status atual da reserva", example = "CONFIRMADA")
        ReservaStatus status,
        @Schema(description = "Observações adicionais registradas na reserva", example = "Mesa perto da janela")
        String observacoes,
        MesaResponse mesa
) {}
