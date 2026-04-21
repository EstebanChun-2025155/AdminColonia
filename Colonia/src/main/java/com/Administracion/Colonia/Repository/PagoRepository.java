package com.Administracion.Colonia.Repository;

import com.Administracion.Colonia.Entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    Boolean existsByIdResidenteAndClasificacionPagoAndMontoAndFechaPagoAndMetodoAndReferencia (
            Integer idResidente,
            String clasificacionPago,
            Double monto,
            LocalDate fechaPago,
            String metodo,
            String referencia
    );
}