package com.Administracion.Colonia.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "Residente")
public class Residente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_Residente")
    private Integer idResidente;

    @NotBlank(message = "Los campos no pueden estar vacios")
    @Column (name = "nombre_Residente")
    private String nombreResidente;

    @NotBlank(message = "Los campos no pueden estar vacios")
    @Size(min = 13, max = 13, message = "El DPI debe tener exactamente 13 caracteres")
    @Column (name = "dpi_Residente")
    private String dpiResidente;

    @NotBlank(message = "Los campos no pueden estar vacios")
    @Pattern(regexp = "^(activo|inactivo)$",
            message = "La posicion solo es valida bajo los dominios: activo, inactivo")
    @Column (name = "posicion")
    private String posicion;

    public Integer getIdResidente() {
        return idResidente;
    }

    public void setIdResidente(Integer idResidente) {
        this.idResidente = idResidente;
    }

    public String getNombreResidente() {
        return nombreResidente;
    }

    public void setNombreResidente(String nombreResidente) {
        this.nombreResidente = nombreResidente;
    }

    public String getDpiResidente() {
        return dpiResidente;
    }

    public void setDpiResidente(String dpiResidente) {
        this.dpiResidente = dpiResidente;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}