package Control.Colonia.Service;

import com.Administracion.Colonia.Entity.Visitas;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface VisitasService {
    List<Visitas> getAllVisitas();
    Visitas getVisitasById(Integer id);
    Visitas saveVisitas(Visitas visitas) throws RuntimeException;
    Visitas updateVisitas(Integer id, Visitas visitas);
    void deleteVisitas(Integer id);
}