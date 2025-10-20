package devkaik.TrabalhoFaculdade.Model.Entity;

import devkaik.TrabalhoFaculdade.Model.Enums.ReservaStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nomeCliente;

    @Column(nullable = false, length = 120)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime horario;

    @Column(nullable = false)
    private Integer numeroPessoas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReservaStatus status;

    @Column(length = 255)
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;
}
