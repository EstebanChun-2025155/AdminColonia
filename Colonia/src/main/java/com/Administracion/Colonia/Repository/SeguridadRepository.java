package com.Administracion.Colonia.Repository;

import com.Administracion.Colonia.Entity.Seguridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface SeguridadRepository extends JpaRepository<Seguridad, Integer> {
    Boolean existsByNombreAndPuestoAndJornadaAndSalarioAndTelefono(
            String nombre,
            String puesto,
            String jornada,
            Double salario,
            String telefono
    );
}
