package com.Administracion.Colonia.service;

import com.Administracion.Colonia.entity.Amenidad;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AmenidadService {

    List<Amenidad> getAllAmenidad();

    Amenidad getAmenidadByid(Integer id);

    Amenidad saveAmenidad(Amenidad amenidad) throws RuntimeException;

    Amenidad updateAmenidad(Integer id, Amenidad amenidad);

    void deleteAmenidad(Integer id);
}

