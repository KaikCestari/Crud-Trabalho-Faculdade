package devkaik.TrabalhoFaculdade.Controller;

import devkaik.TrabalhoFaculdade.DTO.UsuarioResponse;
import devkaik.TrabalhoFaculdade.Model.Entity.Usuario;
import devkaik.TrabalhoFaculdade.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponse> usuarioAtual(@AuthenticationPrincipal Usuario usuario) {
        if (usuario == null) {
            return ResponseEntity.status(401).build();
        }
        UsuarioResponse response = new UsuarioResponse(usuario.getId(), usuario.getName(), usuario.getEmail(), usuario.getUsuarioEnum());
        return ResponseEntity.ok(response);
    }
}
