package com.Administracion.Colonia.Repository;

import com.Administracion.Colonia.Entity.Residente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidenteRepository extends JpaRepository<Residente, Integer> {

    Residente findByNombreResidenteAndDpiResidente(String nombreResidente, String dpiResidente);

}