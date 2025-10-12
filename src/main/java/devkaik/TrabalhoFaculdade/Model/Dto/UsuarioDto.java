package devkaik.TrabalhoFaculdade.Model.Dto;

import devkaik.TrabalhoFaculdade.Model.Enums.UsuarioEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(
        @NotNull
        String name,
       @NotNull
       String email,
        String senha,
        UsuarioEnum usuarioEnum
) {
}
