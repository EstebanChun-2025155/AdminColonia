package com.Administracion.Colonia.repository;

import com.Administracion.Colonia.entity.Amenidad;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AmenidadRepository extends JpaRepository<Amenidad,Integer> {
    Boolean existsByNombreAmenidadAndHorarioUsoAndCostoUsoAndEstadoAndCapacidad(
            String nombreAmenidad,
            String horarioUso,
            double costoUso,
            String estado,
            Integer capacidad
    );

    }
