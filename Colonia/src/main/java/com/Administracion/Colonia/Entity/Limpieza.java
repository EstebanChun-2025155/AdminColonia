package com.Administracion.Colonia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "limpieza")
public class Limpieza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Limpieza")
    private Integer idLimpieza;

    @NotBlank(message = "El campo de nombre no debe de estar vacios")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "El campo de puesto no debe de estar vacios")
    @Column(name = "puesto")
    private String puesto;

    @NotBlank(message = "El campo de jornada no debe de estar vacios")
    @Pattern( regexp  = "^(manana|tarde)$",
            message =  "El estado es valido unicamente bajo la jornada: manana o tarde")
    @Column(name = "jornada")
    private String jornada;

    @NotNull(message = "El salario no debe estar vacío")
    @DecimalMin(value = "3100.00", message = "El salario debe ser al menos de 3100.00")
    @Column(name = "salario")
    private Double salario;

    @NotBlank(message = "El telefono no debe estar vacío")
    @Pattern(regexp = "^\\d{4}-\\d{4}$", message = "El telefono debe tener formato 0000-0000")
    @Column(name = "telefono")
    private String telefono;

    public Integer getIdLimpieza() {
        return idLimpieza;
    }

    public void setIdLimpieza(Integer idLimplieza) {
        this.idLimpieza = idLimplieza;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
