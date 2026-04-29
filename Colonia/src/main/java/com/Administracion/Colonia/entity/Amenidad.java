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

    @Min(value = 0, message = "El costo no puede ser negativo")
    @Column(name = "costoUso")
    private double costoUso;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "^(?i)(ocupada|disponible|mantenimiento)$", message = "Estado no válido")
    @Column(name = "estado", nullable = false)
    private String estado;

    @Min(value = 0, message = "La capacidad no puede ser menor a 0")
    @Column(name = "capacidad")
    private Integer capacidad;
    public Integer getIdAmenidad() {
        return idAmenidad;
    }

    public void setIdAmenidad(Integer idAmenidad) {
        this.idAmenidad = idAmenidad;
    }

    public @NotBlank(message = "El nombre es obligatorio") String getNombreAmenidad() {
        return nombreAmenidad;
    }

    public void setNombreAmenidad(@NotBlank(message = "El nombre es obligatorio") String nombreAmenidad) {
        this.nombreAmenidad = nombreAmenidad;
    }

    public @NotBlank(message = "El horario es obligatorio") String getHorarioUso() {
        return horarioUso;
    }

    public void setHorarioUso(@NotBlank(message = "El horario es obligatorio") String horarioUso) {
        this.horarioUso = horarioUso;
    }

    public @Min(value = 0, message = "El costo no puede ser negativo") double getCostoUso() {
        return costoUso;
    }

    public void setCostoUso(@Min(value = 0, message = "El costo no puede ser negativo") double costoUso) {
        this.costoUso = costoUso;
    }

    public @NotBlank(message = "El estado es obligatorio") @Pattern(regexp = "^(ocupada|disponible|mantenimiento)$",
            message = "Estado no válido") String getEstado() {
        return estado;
    }

    public void setEstado(@NotBlank(message = "El estado es obligatorio") @Pattern(regexp = "^(ocupada|disponible|mantenimiento)$",
            message = "Estado no válido") String estado) {
        this.estado = estado;
    }

    public @Min(value = 0, message = "La capacidad no puede ser menor a 0") Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(@Min(value = 0, message = "La capacidad no puede ser menor a 0") Integer capacidad) {
        this.capacidad = capacidad;
    }
}
