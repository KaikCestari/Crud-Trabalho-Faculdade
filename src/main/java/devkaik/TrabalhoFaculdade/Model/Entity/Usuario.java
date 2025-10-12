package devkaik.TrabalhoFaculdade.Model.Entity;

import devkaik.TrabalhoFaculdade.Model.Enums.UsuarioEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name ="tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private  String senha;
    private UsuarioEnum usuarioEnum;
}
