package devkaik.TrabalhoFaculdade.DTO;

import devkaik.TrabalhoFaculdade.Model.Enums.UsuarioEnum;

public record UsuarioResponse(
        Long id,
        String name,
        String email,
        UsuarioEnum usuarioEnum
) {
}
