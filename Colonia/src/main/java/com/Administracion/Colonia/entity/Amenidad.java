package com.Administracion.Colonia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Amenidades")
public class Amenidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAmenidad")
    private Integer idAmenidad;

    @NotBlank(message = "Debe rellenar el nombre")
    @Column(name = "nombreAmenidad")
    private String nombreAmenidad;

    @NotBlank(message = "Debe rellenar el horario")
    @Column(name = "horarioUso")
    private String horarioUso;

    @Column(name = "costoUso")
    private double costoUso;

    @NotBlank(message = "El campo de texto no debe de estar vacios")
    @Pattern( regexp  = "^(ocupada|disponible|mantenimiento)$",
            message =  "El estado es valido unicamente bajo los dominios: ocupada, disponible, mantenimiento")
    @NotBlank(message = "Se agrego el campos")

    @Column (name = "estado", nullable = false)
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

    public double getCostoUso() {
        return costoUso;
    }

    public void setCostoUso(double costoUso) {
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
