package Control.Colonia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "Amenidades")
public class Amenidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAmenidad")
    private Integer idAmenidad;

    @Column(name = "idResidente")
    private Integer idResidente;

    @NotBlank(message = "El nombre de la amenidad es obligatorio")
    @Pattern(regexp = "(?i)^(salon social|piscina|cancha deportiva|lounge de estudio|terraza|cinema)$",
            message = "La amenidad solo puede ser: Salon social, piscina, cancha deportiva, lounge de estudio, terraza, cinema"
    )
    @Column(name = "nombreAmenidad")
    private String nombreAmenidad;

    @NotBlank(message = "El horario es obligatorio")
    @Column(name = "horario")
    private String horario;

    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día actual")
    @Column(name = "fecha")
    private LocalDate fecha ;

    @DecimalMin(value = "0.00", message = "El costo no puede ser negativo")
    @Column(name = "costoUso")
    private Double costoUso;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "(?i)^(reservado|pendiente|mantenimiento)$",
            message = "El estado solo puede ser: reservado, pendiente o mantenimiento"
    )
    @Column(name = "estado")
    private String estado;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser mayor a 0")
    @Column(name = "capacidad")
    private Integer capacidad;

    public Integer getIdAmenidad() {
        return idAmenidad;
    }

    public void setIdAmenidad(Integer idAmenidad) {
        this.idAmenidad = idAmenidad;
    }

    public Integer getIdResidente() {
        return idResidente;
    }

    public void setIdResidente(Integer idResidente) {
        this.idResidente = idResidente;
    }

    public String getNombreAmenidad() {
        return nombreAmenidad;
    }

    public void setNombreAmenidad(String nombreAmenidad) {
        this.nombreAmenidad = nombreAmenidad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getCostoUso() {
        return costoUso;
    }

    public void setCostoUso(Double costoUso) {
        this.costoUso = costoUso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
}