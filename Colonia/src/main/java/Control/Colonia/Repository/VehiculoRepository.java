package Control.Colonia.Repository;

import Control.Colonia.Entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    // Solo necesitamos verificar la placa porque es el único campo UNIQUE
    boolean existsByPlaca(String placa);
}
