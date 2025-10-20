package devkaik.TrabalhoFaculdade.controller.dto;

import devkaik.TrabalhoFaculdade.Model.Enums.UsuarioEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Email(message = "E-mail inválido")
        @NotBlank(message = "E-mail é obrigatório")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        String senha,

        UsuarioEnum perfil
) {
}
