package Control.Colonia.Repository;

import Control.Colonia.Entity.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
    Boolean existsByIdResidenteReportanteAndIdResidenteReportadoAndTipoReporteAndDescripcionAndFechaReporteAndEstado(
            Integer idResidenteReportante,
            Integer idResidenteReportado,
            String tipoReporte,
            String descripcion,
            LocalDate fechaReporte,
            String estado
    );

    List<Reporte> findByIdResidenteReportado(Integer idResidenteReportado);
    List<Reporte> findByIdResidenteReportante(Integer idResidenteReportante);
}