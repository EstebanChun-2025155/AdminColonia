package Control.Colonia.Repository;

import Control.Colonia.Entity.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Integer> {
    Boolean existsByMontoAndDescripcionAndFechaEmisionAndEstadoAndTipoPersona (
            Double monto,
            String descripcion,
            LocalDate fechaEmision,
            String estado,
            String tipoPersona
    );
}