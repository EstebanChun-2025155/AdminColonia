package com.Administracion.Colonia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Amenidades")
public class Amenidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAmenidad")
    private Integer idAmenidad;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombreAmenidad")
    private String nombreAmenidad;

    @NotBlank(message = "El horario es obligatorio")
    @Column(name = "horarioUso")
    private String horarioUso;

    @NotNull(message = "El costo es obligatorio") // Ahora sí puede detectar si está vacío
    @Min(value = 0, message = "El costo no puede ser negativo")
    @Column(name = "costoUso")
    private Double costoUso;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "^(?i)(ocupada|reservacion|mantenimiento)$", message = "Estado no válido")
    @Column(name = "estado", nullable = false)
    private String estado;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser al menos 1")
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
