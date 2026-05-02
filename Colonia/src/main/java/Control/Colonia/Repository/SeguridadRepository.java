package Control.Colonia.Repository;

import Control.Colonia.Entity.Seguridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface SeguridadRepository extends JpaRepository<Seguridad, Integer> {
    Boolean existsByNombreAndPuestoAndJornadaAndSalarioAndTelefonoSeguridadAndDpiSeguridad(
            String nombre,
            String puesto,
            String jornada,
            Double salario,
            String telefono,
            String dpiSeguridad
    );

    Seguridad findByNombreAndDpiSeguridad(String nombreSeguridad, String dpiSeguridad);
}
