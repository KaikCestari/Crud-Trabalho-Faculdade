package devkaik.TrabalhoFaculdade.repository;

import devkaik.TrabalhoFaculdade.Model.Entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
    Optional<Mesa> findByIdentificadorIgnoreCase(String identificador);
}
