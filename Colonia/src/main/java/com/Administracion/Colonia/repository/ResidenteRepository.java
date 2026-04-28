package com.Administracion.Colonia.repository;

import com.Administracion.Colonia.entity.Residente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenteRepository extends JpaRepository<Residente, Integer> {

    Residente findByNombreResidenteAndDpiResidente(String nombreResidente,
                                                   String dpiResidente);
}