package Control.Colonia.Repository;

import Control.Colonia.Entity.Amenidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenidadRepository extends JpaRepository<Amenidad,Integer> {
    Boolean existsByNombreAmenidadAndHorarioUsoAndCostoUsoAndEstadoAndCapacidad(
            String nombreAmenidad,
            String horarioUso,
            double costoUso,
            String estado,
            Integer capacidad
    );

    }
