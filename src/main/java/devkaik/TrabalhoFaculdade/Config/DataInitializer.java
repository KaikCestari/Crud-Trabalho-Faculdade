package devkaik.TrabalhoFaculdade.Config;

import devkaik.TrabalhoFaculdade.Model.Entity.Usuario;
import devkaik.TrabalhoFaculdade.Model.Enums.UsuarioEnum;
import devkaik.TrabalhoFaculdade.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner criarAdministradorPadrao() {
        return args -> {
            if (!usuarioRepository.existsByEmail("admin@faculdade.com")) {
                Usuario admin = Usuario.builder()
                        .name("Administrador")
                        .email("admin@faculdade.com")
                        .senha(passwordEncoder.encode("Admin@123"))
                        .usuarioEnum(UsuarioEnum.ADMIN)
                        .build();
                usuarioRepository.save(admin);
            }
        };
    }
}
