package com.Administracion.Colonia.service;

import com.Administracion.Colonia.entity.Residente;

public interface ResidenteService {
    Residente login(String nombreResidente, String dpiResidente);
}