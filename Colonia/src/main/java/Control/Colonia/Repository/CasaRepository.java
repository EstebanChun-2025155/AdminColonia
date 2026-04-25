package Control.Colonia.Repository;

import Control.Colonia.Entity.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CasaRepository extends JpaRepository<Casa, Integer>{
    Boolean existsByNoDeCasaAndDireccionAndEstadoAndPropietarioAndPrecioCasa(
            String noDeCasa,
            String direccion,
            String estado,
            String propietario,
            Double precioCasa
    );
}