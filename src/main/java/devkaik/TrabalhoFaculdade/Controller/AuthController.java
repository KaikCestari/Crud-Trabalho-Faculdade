package devkaik.TrabalhoFaculdade.Controller;

import devkaik.TrabalhoFaculdade.DTO.LoginRequest;
import devkaik.TrabalhoFaculdade.DTO.RegisterRequest;
import devkaik.TrabalhoFaculdade.DTO.TokenResponse;
import devkaik.TrabalhoFaculdade.DTO.UsuarioResponse;
import devkaik.TrabalhoFaculdade.Service.AuthService;
import devkaik.TrabalhoFaculdade.Service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.autenticar(request);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> registrar(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(usuarioService.criarUsuario(request));
    }
}
