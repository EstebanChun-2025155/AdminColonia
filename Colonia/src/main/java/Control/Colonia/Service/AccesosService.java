package Control.Colonia.Service;

import Control.Colonia.Entity.Accesos;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccesosService {
    List<Accesos> getAllAccesos();
    Accesos getAccesosById(Integer id);
    Accesos saveAcceso(Accesos accesos);
    Accesos updateAcceso(Integer id, Accesos accesos);
    void deleteAcceso(Integer id);
}