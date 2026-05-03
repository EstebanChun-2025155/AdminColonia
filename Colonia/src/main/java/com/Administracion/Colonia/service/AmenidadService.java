package com.Administracion.Colonia.service;

import com.Administracion.Colonia.entity.Amenidad;

import java.util.List;
import java.util.Optional;

public interface AmenidadService {

    List<Amenidad> getAllAmenidad();

    Amenidad getAmenidadById(Integer id);

    Amenidad saveAmenidad(Amenidad amenidad) throws RuntimeException;

    Amenidad updateAmenidad(Integer id, Amenidad amenidad);

    void deleteAmenidad(Integer id);

    Optional<Amenidad> buscarPorId(Integer id);


}