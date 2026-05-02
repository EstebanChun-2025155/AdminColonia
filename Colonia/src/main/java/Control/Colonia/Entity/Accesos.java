package Control.Colonia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "Accesos")
public class Accesos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Acceso")
    private Integer id;

    @NotBlank(message = "El campo de texto no debe de estar vacio")
    @Pattern( regexp  = "^(visita|residente|personal)$",
            message =  "El tipo de persona debe ser: visita, residente o personal")
    @Column(name = "tipo_Persona")
    private String tipoPersona;

    @NotNull(message = "El campo no puede estar vacio")
    @Column(name = "id_Seguridad")
    private Integer idSeguridad;

    @NotNull(message = "La hora de entrada no puede estar vacía")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "hora_Entrada")
    private LocalDateTime horaEntrada;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "hora_Salida")
    private LocalDateTime horaSalida;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public Integer getIdSeguridad() {
        return idSeguridad;
    }

    public void setIdSeguridad(Integer idSeguridad) {
        this.idSeguridad = idSeguridad;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }
}