package com.Administracion.Colonia.service;


import com.Administracion.Colonia.entity.Amenidad;
import com.Administracion.Colonia.repository.AmenidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenidadServiceimplements  implements AmenidadService {
    private final AmenidadRepository amenidadRepository;

    public AmenidadServiceimplements(AmenidadRepository amenidadRepository) {
        this.amenidadRepository = amenidadRepository;
    }

    @Override
    public List<Amenidad> getAllAmenidad() {
        return amenidadRepository.findAll();
    }

    @Override
    public Amenidad getAmenidadByid(Integer id) {
        return amenidadRepository.getReferenceById(id);
    }


    @Override
    public Amenidad saveAmenidad(Amenidad amenidad) throws RuntimeException {
        try {
            if (amenidadRepository.existsByNombreAmenidadAndHorarioUsoAndCostoUsoAndEstadoAndCapacidad(
                    amenidad.getNombreAmenidad(),
                    amenidad.getHorarioUso(),
                    amenidad.getCostoUso(),
                    amenidad.getEstado(),
                    amenidad.getCapacidad())) {
                throw new RuntimeException("Ya existe una amenidad con estos datos");
            }

            return amenidadRepository.save(amenidad);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public Amenidad updateAmenidad(Integer id, Amenidad amenidad) {
        Amenidad existingAmenidad = amenidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La amenidad no existe"));

        if (amenidadRepository.existsByNombreAmenidadAndHorarioUsoAndCostoUsoAndEstadoAndCapacidad(
                amenidad.getNombreAmenidad(),
                amenidad.getHorarioUso(),
                amenidad.getCostoUso(),
                amenidad.getEstado(),
                amenidad.getCapacidad()
        )) {
            throw new RuntimeException("Ya existe una amenidad con estos datos");
        }

        existingAmenidad.setNombreAmenidad(amenidad.getNombreAmenidad());
        existingAmenidad.setHorarioUso(amenidad.getHorarioUso());
        existingAmenidad.setCostoUso(amenidad.getCostoUso());
        existingAmenidad.setEstado(amenidad.getEstado());
        existingAmenidad.setCapacidad(amenidad.getCapacidad());

        return amenidadRepository.save(existingAmenidad);
    }

    @Override
    public void deleteAmenidad(Integer id) {
        if (!amenidadRepository.existsById(id)) {
            throw new RuntimeException("Este id no existe");

        }
        amenidadRepository.deleteById(id);
    }
}