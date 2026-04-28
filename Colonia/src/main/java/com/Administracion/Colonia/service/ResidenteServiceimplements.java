package com.Administracion.Colonia.service;

import com.Administracion.Colonia.entity.Residente;
import com.Administracion.Colonia.repository.ResidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenteServiceimplements implements ResidenteService {

    @Autowired
    private ResidenteRepository residenteRepository;

    @Override
    public Residente login(String nombreResidente, String dpiResidente) {
        return residenteRepository.findByNombreResidenteAndDpiResidente(nombreResidente, dpiResidente);
    }
}