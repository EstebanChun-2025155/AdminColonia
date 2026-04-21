package com.Administracion.Colonia.Repository;

import com.Administracion.Colonia.Entity.Accesos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AccesosRepository  extends JpaRepository<Accesos, Integer> {
    Boolean existsByTipoPersonaAndIdSeguridadAndHoraEntradaAndHoraSalida(
            String tipoPersona,
            Integer idSeguridad,
            LocalDateTime horaEntrada,
            LocalDateTime horaSalida
    );
}