package com.Administracion.Colonia.Service;

import com.Administracion.Colonia.Entity.Seguridad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeguridadService {
    List<Seguridad> getAllSeguridad();
    Seguridad getSeguridadById(Integer id);
    Seguridad saveSeguridad (Seguridad seguridad) throws RuntimeException;
    Seguridad updateSeguridad(Integer id, Seguridad seguridad);
    void deleteSeguridad(Integer id);
}