package devkaik.TrabalhoFaculdade.DTO;

import devkaik.TrabalhoFaculdade.Model.Enums.UsuarioEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String senha,
        @NotNull UsuarioEnum usuarioEnum
) {
}
