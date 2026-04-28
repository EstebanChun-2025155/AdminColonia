package com.Administracion.Colonia.Service;

import com.Administracion.Colonia.Entity.Residente;
import org.springframework.stereotype.Service;

@Service
public interface ResidenteService {
    Residente login(String nombreResidente, String dpiResidente);
}