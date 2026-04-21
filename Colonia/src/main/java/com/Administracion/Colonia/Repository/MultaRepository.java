package com.Administracion.Colonia.Repository;

import com.Administracion.Colonia.Entity.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Integer> {
    Boolean existsByMontoAndDescripcionAndFechaEmisionAndEstadoAndTipoPersona (
            Double monto,
            String descripcion,
            LocalDate fechaEmision,
            String estado,
            String tipoPersona
    );
}