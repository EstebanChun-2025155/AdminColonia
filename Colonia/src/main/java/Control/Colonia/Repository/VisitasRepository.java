package Control.Colonia.Repository;

import Control.Colonia.Entity.Visitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitasRepository extends JpaRepository<Visitas, Integer> {
    Boolean existsByNombreVisitaAndDocumentoAndPlacaAndMotivoAndIdCasa(
            String nombreVisita,
            String documento,
            String placa,
            String motivo,
            Integer idCasa
    );
}