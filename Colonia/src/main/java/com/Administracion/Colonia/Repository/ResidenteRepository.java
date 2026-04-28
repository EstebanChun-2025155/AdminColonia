package com.Administracion.Colonia.Repository;

import com.Administracion.Colonia.Entity.Residente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenteRepository extends JpaRepository<Residente, Integer> {
    Boolean existsByNombreResidenteAndDpiResidenteAndTelefonoResidenteAndPosicionAndIdCasa(
            String nombreResidente,
            String dpiResidente,
            String telefonoResidente,
            String posicion,
            Integer idCasa
    );

    Residente findByNombreResidenteAndDpiResidente(String nombreResidente, String dpiResidente);
}