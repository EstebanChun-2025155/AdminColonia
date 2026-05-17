package Control.Colonia.Service;

import Control.Colonia.Entity.Reporte;
import Control.Colonia.Repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteServiceImplements implements ReporteService{

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public List<Reporte> getAllReporte() {
        return reporteRepository.findAll();
    }

    @Override
    public List<Reporte> buscarporReportante(Integer id) {
        return reporteRepository.findByIdResidenteReportante(id);
    }


    @Override
    public Reporte getReporteById(Integer id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El reporte no existe"));
    }

    @Override
    public Reporte saveReporte(Reporte reporte) throws RuntimeException {
        try {
            if (reporteRepository.existsByIdResidenteReportanteAndIdResidenteReportadoAndTipoReporteAndDescripcionAndFechaReporteAndEstado(
                    reporte.getIdResidenteReportante(),
                    reporte.getIdResidenteReportado(),
                    reporte.getTipoReporte(),
                    reporte.getDescripcion(),
                    reporte.getFechaReporte(),
                    reporte.getEstado())) {
                throw new RuntimeException("Ya existe un reporte con estos datos");
            }
            return reporteRepository.save(reporte);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Reporte updateReporte(Integer id, Reporte reporte) {
        Reporte existingReporte = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El reporte no existe"));

        if (reporteRepository.existsByIdResidenteReportanteAndIdResidenteReportadoAndTipoReporteAndDescripcionAndFechaReporteAndEstado(
                reporte.getIdResidenteReportante(),
                reporte.getIdResidenteReportado(),
                reporte.getTipoReporte(),
                reporte.getDescripcion(),
                reporte.getFechaReporte(),
                reporte.getEstado())) {
            throw new RuntimeException("Ya existe un reporte con estos datos");
        }

        existingReporte.setIdResidenteReportante(reporte.getIdResidenteReportante());
        existingReporte.setIdResidenteReportado(reporte.getIdResidenteReportado());
        existingReporte.setTipoReporte(reporte.getTipoReporte());
        existingReporte.setDescripcion(reporte.getDescripcion());
        existingReporte.setFechaReporte(reporte.getFechaReporte());
        existingReporte.setEstado(reporte.getEstado());

        return reporteRepository.save(existingReporte);
    }

    @Override
    public void deleteReporte(Integer id) {
        reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El reporte no existe"));
        reporteRepository.deleteById(id);
    }

    @Override
    public List<Reporte> buscarPorReportado(Integer id) {
        return reporteRepository.findByIdResidenteReportado(id);
    }

}