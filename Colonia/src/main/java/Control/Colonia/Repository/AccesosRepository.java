package Control.Colonia.Repository;

import Control.Colonia.Entity.Accesos;
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