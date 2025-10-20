package devkaik.TrabalhoFaculdade.mapper;

import devkaik.TrabalhoFaculdade.Model.Entity.Mesa;
import devkaik.TrabalhoFaculdade.dto.MesaRequest;
import devkaik.TrabalhoFaculdade.dto.MesaResponse;

public final class MesaMapper {

    private MesaMapper() {
    }

    public static Mesa toEntity(MesaRequest request) {
        return Mesa.builder()
                .identificador(request.identificador())
                .capacidade(request.capacidade())
                .build();
    }

    public static void updateEntity(Mesa mesa, MesaRequest request) {
        mesa.setIdentificador(request.identificador());
        mesa.setCapacidade(request.capacidade());
    }

    public static MesaResponse toResponse(Mesa mesa) {
        return new MesaResponse(mesa.getId(), mesa.getIdentificador(), mesa.getCapacidade());
    }
}
