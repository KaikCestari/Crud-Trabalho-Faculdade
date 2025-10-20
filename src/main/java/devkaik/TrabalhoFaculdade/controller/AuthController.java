package devkaik.TrabalhoFaculdade.controller;

import devkaik.TrabalhoFaculdade.Model.Entity.Usuario;
import devkaik.TrabalhoFaculdade.controller.dto.LoginRequest;
import devkaik.TrabalhoFaculdade.controller.dto.TokenResponse;
import devkaik.TrabalhoFaculdade.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.email(), request.senha());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        Usuario usuario = (Usuario) authentication.getPrincipal();

        String token = tokenService.generateToken(usuario);
        long expiresIn = Duration.ofHours(1).toSeconds();

        return ResponseEntity.ok(new TokenResponse(token, "Bearer", expiresIn));
    }
}
