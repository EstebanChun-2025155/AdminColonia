package Control.Colonia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Amenidades")
public class Amenidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAmenidad")
    private Integer idAmenidad;

    @NotBlank(message = "El nombre de la amenidad es obligatorio")
    @Pattern(regexp = "(?i)^(piscina|cancha de futbol|salon social|área de bbq|lounge de estudio|cinema|salón de juegos)$",
            message = "La amenidad solo puede ser: Piscina, cancha de futbol, salon social, Área de BBQ, lounge de estudio, cinema o salón de juegos"
    )
    @Column(name = "nombreAmenidad")
    private String nombreAmenidad;

    @NotBlank(message = "El horario es obligatorio")
    @Column(name = "horarioUso")
    private String horarioUso;

    @NotNull(message = "El costo es obligatorio")
    @DecimalMin(value = "0.00", message = "El costo no puede ser negativo")
    @Column(name = "costoUso")
    private Double costoUso;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "(?i)^(ocupada|disponible|mantenimiento)$",
            message = "El estado solo puede ser: ocupada, disponible o mantenimiento"
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

    public String getNombreAmenidad() {
        return nombreAmenidad;
    }

    public void setNombreAmenidad(String nombreAmenidad) {
        this.nombreAmenidad = nombreAmenidad;
    }

    public String getHorarioUso() {
        return horarioUso;
    }

    public void setHorarioUso(String horarioUso) {
        this.horarioUso = horarioUso;
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