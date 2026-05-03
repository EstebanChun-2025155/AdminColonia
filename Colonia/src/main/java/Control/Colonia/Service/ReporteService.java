package Control.Colonia.Service;

import Control.Colonia.Entity.Reporte;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReporteService {
    List<Reporte> getAllReporte();
    List<Reporte> buscarPorReportado(Integer id);
    Reporte getReporteById(Integer id);
    Reporte saveReporte(Reporte reporte) throws RuntimeException;
    Reporte updateReporte(Integer id, Reporte reporte);
    void deleteReporte(Integer id);

}