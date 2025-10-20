package devkaik.TrabalhoFaculdade.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Sistema de Reservas de Restaurante",
                version = "v1",
                description = "API REST para gerenciamento de mesas e reservas de um restaurante.",
                contact = @Contact(name = "Equipe de Desenvolvimento", email = "contato@restaurante.com"),
                license = @License(name = "MIT License", url = "https://opensource.org/license/mit/")
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Ambiente local")
        },
        tags = {
                @Tag(name = "Mesas", description = "Operações relacionadas às mesas do restaurante"),
                @Tag(name = "Reservas", description = "Operações relacionadas às reservas de clientes")
        }
)
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@Configuration
public class OpenApiConfig {
}
