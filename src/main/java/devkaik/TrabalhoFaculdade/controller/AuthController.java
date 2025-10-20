package devkaik.TrabalhoFaculdade.controller;

import devkaik.TrabalhoFaculdade.Model.Entity.Usuario;
import devkaik.TrabalhoFaculdade.Model.Enums.UsuarioEnum;
import devkaik.TrabalhoFaculdade.Model.Repository.UsuarioRepository;
import devkaik.TrabalhoFaculdade.controller.dto.LoginRequest;
import devkaik.TrabalhoFaculdade.controller.dto.RegisterRequest;
import devkaik.TrabalhoFaculdade.controller.dto.TokenResponse;
import devkaik.TrabalhoFaculdade.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.email(), request.senha());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        Usuario usuario = (Usuario) authentication.getPrincipal();

        String token = jwtService.generateToken(usuario);
        long expiresIn = jwtService.getExpirationInSeconds();

        return ResponseEntity.ok(new TokenResponse(token, "Bearer", expiresIn));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setName(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        UsuarioEnum perfil = Optional.ofNullable(request.perfil()).orElse(UsuarioEnum.FUNCIONARIO);
        usuario.setUsuarioEnum(perfil);

        usuarioRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
