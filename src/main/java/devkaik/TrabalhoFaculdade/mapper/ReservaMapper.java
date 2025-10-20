package devkaik.TrabalhoFaculdade.mapper;

import devkaik.TrabalhoFaculdade.Model.Entity.Mesa;
import devkaik.TrabalhoFaculdade.Model.Entity.Reserva;
import devkaik.TrabalhoFaculdade.Model.Enums.ReservaStatus;
import devkaik.TrabalhoFaculdade.dto.MesaResponse;
import devkaik.TrabalhoFaculdade.dto.ReservaRequest;
import devkaik.TrabalhoFaculdade.dto.ReservaResponse;

public final class ReservaMapper {

    private ReservaMapper() {
    }

    public static Reserva toEntity(ReservaRequest request, Mesa mesa) {
        return Reserva.builder()
                .nomeCliente(request.nomeCliente())
                .email(request.email())
                .telefone(request.telefone())
                .data(request.data())
                .horario(request.horario())
                .numeroPessoas(request.numeroPessoas())
                .status(request.status() != null ? request.status() : ReservaStatus.AGENDADA)
                .observacoes(request.observacoes())
                .mesa(mesa)
                .build();
    }

    public static void updateEntity(Reserva reserva, ReservaRequest request, Mesa mesa) {
        reserva.setNomeCliente(request.nomeCliente());
        reserva.setEmail(request.email());
        reserva.setTelefone(request.telefone());
        reserva.setData(request.data());
        reserva.setHorario(request.horario());
        reserva.setNumeroPessoas(request.numeroPessoas());
        reserva.setStatus(request.status() != null ? request.status() : reserva.getStatus());
        reserva.setObservacoes(request.observacoes());
        reserva.setMesa(mesa);
    }

    public static ReservaResponse toResponse(Reserva reserva) {
        Mesa mesa = reserva.getMesa();
        MesaResponse mesaResponse = mesa != null ? MesaMapper.toResponse(mesa) : null;
        return new ReservaResponse(
                reserva.getId(),
                reserva.getNomeCliente(),
                reserva.getEmail(),
                reserva.getTelefone(),
                reserva.getData(),
                reserva.getHorario(),
                reserva.getNumeroPessoas(),
                reserva.getStatus(),
                reserva.getObservacoes(),
                mesaResponse
        );
    }
}
