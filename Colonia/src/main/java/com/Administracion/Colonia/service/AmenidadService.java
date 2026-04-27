package com.Administracion.Colonia.service;

import com.Administracion.Colonia.entity.Amenidad;

import java.util.List;

public interface AmenidadService {

    List<Amenidad> getAllAmenidad();

    Amenidad getAmenidadById(Integer id);

    Amenidad saveAmenidad(Amenidad amenidad) throws RuntimeException;

    Amenidad updateAmenidad(Integer id, Amenidad amenidad);

    void deleteAmenidad(Integer id);
}