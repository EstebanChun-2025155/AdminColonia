package Control.Colonia.Repository;

import Control.Colonia.Entity.Limpieza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface LimpiezaRepository extends JpaRepository<Limpieza, Integer> {
    Boolean existsByNombreAndPuestoAndJornadaAndSalarioAndTelefono(
            String nombre,
            String puesto,
            String jornada,
            Double salario,
            String telefono
    );
}