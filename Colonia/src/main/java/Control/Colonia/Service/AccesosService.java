package com.Administracion.Colonia.Service;

import com.Administracion.Colonia.Entity.Accesos;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccesosService {
    List<Accesos> getAllAccesos();
    Accesos getAccesosById(Integer id);
    Accesos saveAcceso(Accesos accesos) throws RuntimeException;
    Accesos updateAcceso(Integer id, Accesos accesos);
    void deleteAccesos(Integer id);
}