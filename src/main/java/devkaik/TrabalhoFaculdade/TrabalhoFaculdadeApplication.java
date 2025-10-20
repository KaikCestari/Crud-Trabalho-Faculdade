package devkaik.TrabalhoFaculdade;

import devkaik.TrabalhoFaculdade.Model.Entity.Usuario;
import devkaik.TrabalhoFaculdade.Model.Enums.UsuarioEnum;
import devkaik.TrabalhoFaculdade.Model.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TrabalhoFaculdadeApplication {

        public static void main(String[] args) {
                SpringApplication.run(TrabalhoFaculdadeApplication.class, args);
        }

        @Bean
        CommandLineRunner initDefaultUser(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
                return args -> usuarioRepository.findByEmail("admin@faculdade.com")
                        .orElseGet(() -> usuarioRepository.save(new Usuario(
                                null,
                                "Administrador",
                                "admin@faculdade.com",
                                passwordEncoder.encode("admin123"),
                                UsuarioEnum.ADMIN
                        )));
        }
}
