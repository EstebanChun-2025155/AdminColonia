package com.Administracion.Colonia.Repository;

import com.Administracion.Colonia.Entity.Seguridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface SeguridadRepository extends JpaRepository<Seguridad, Integer> {
    Boolean existsByNombreAndPuestoAndJornadaAndSalarioAndTelefonoSeguridadAndDpiSeguridad(
            String nombre,
            String puesto,
            String jornada,
            Double salario,
            String telefono,
            String dpiSeguridad
    );

    Seguridad findByNombreAndDpiSeguridad(String nombreSeguridad, String dpiSeguridad);
}