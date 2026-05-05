package com.Administracion.Colonia.Repository;

import com.Administracion.Colonia.Entity.Limpieza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface LimpiezaRepository extends JpaRepository<Limpieza, Integer> {
    Boolean existsByNombreAndPuestoAndJornadaAndSalarioAndTelefono(
            String nombre,
            String puesto,
            String jornada,
            Double salario,
            String telefono
    );
}