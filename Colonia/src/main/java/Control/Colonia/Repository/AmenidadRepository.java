package Control.Colonia.Repository;

import Control.Colonia.Entity.Amenidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AmenidadRepository extends JpaRepository<Amenidad,Integer> {
    boolean existsByNombreAmenidadAndHorarioAndFecha(
            String nombreAmenidad,
            String horario,
            LocalDate fecha
    );

    boolean existsByNombreAmenidadAndHorarioAndFechaAndIdAmenidadNot(
            String nombreAmenidad,
            String horario,
            LocalDate fecha,
            Integer idAmenidad
    );

    List<Amenidad> findByIdResidente(Integer idResidente);
    }
