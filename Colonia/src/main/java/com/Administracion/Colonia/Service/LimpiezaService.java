package com.Administracion.Colonia.Service;

import com.Administracion.Colonia.Entity.Limpieza;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LimpiezaService {
    List<Limpieza> getAllLimpieza();
    Limpieza getLimpiezaById(Integer id);
    Limpieza saveLimpieza (Limpieza limpieza) throws RuntimeException;
    Limpieza updateLimpieza(Integer id, Limpieza limpieza);
    void deleteLimpieza(Integer id);
}