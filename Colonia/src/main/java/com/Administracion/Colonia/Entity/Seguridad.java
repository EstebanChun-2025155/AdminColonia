package com.Administracion.Colonia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "seguridad")
public class Seguridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Seguridad")
    private Integer idSeguridad;

    @NotBlank(message = "El campo de nombre no debe de estar vacios")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚ ]+$", message = "El nombre no puede contener números ni caracteres especiales")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "El campo de puesto no debe de estar vacios")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚ ]+$", message = "El nombre no puede contener números ni caracteres especiales")
    @Column(name = "puesto")
    private String puesto;

    @NotBlank(message = "El campo de jornada no debe de estar vacios")
    @Pattern( regexp  = "^(dia|noche)$",
            message =  "El estado es valido unicamente bajo la jornada: dia o noche")
    @Column(name = "jornada")
    private String jornada;

    @NotNull(message = "El salario no debe estar vacío")
    @DecimalMin(value = "3400.00", message = "El salario debe ser como mínimo 3400.00")
    @Column(name = "salario")
    private Double salario;

    @NotBlank(message = "El telefono no debe estar vacío")
    @Pattern(regexp = "^\\d{4}-\\d{4}$", message = "El telefono debe tener formato 0000-0000")
    @Column(name = "telefono_Seguridad")
    private String telefonoSeguridad;

    @NotBlank(message = "Los campos no pueden estar vacios")
    @Size(max = 13, message = "El dpi solo puede tener 13 caracteres")
    @Column(name = "dpi_Seguridad")
    private String dpiSeguridad;

    public Integer getIdSeguridad() {
        return idSeguridad;
    }

    public void setIdSeguridad(Integer idSeguridad) {
        this.idSeguridad = idSeguridad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getTelefonoSeguridad() {
        return telefonoSeguridad;
    }

    public void setTelefonoSeguridad(String telefonoSeguridad) {
        this.telefonoSeguridad = telefonoSeguridad;
    }

    public String getDpiSeguridad() {
        return dpiSeguridad;
    }

    public void setDpiSeguridad(String dpiSeguridad) {
        this.dpiSeguridad = dpiSeguridad;
    }
}