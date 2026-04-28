package com.Administracion.Colonia.Service;

import com.Administracion.Colonia.Entity.Residente;
import com.Administracion.Colonia.Repository.ResidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenteServiceImplements implements ResidenteService {

    @Autowired
    private ResidenteRepository residenteRepository;

    @Override
    public Residente login(String nombreResidente, String dpiResidente) {
        return residenteRepository.findByNombreResidenteAndDpiResidente(
                nombreResidente, dpiResidente
        );
    }
}