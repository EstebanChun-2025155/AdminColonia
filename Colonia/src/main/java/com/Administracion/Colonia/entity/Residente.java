package com.Administracion.Colonia.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Residente")
public class Residente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResidente;

    private String nombreResidente;
    private String dpiResidente;
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

    public void setPosicion(String posición) {
        this.posicion = posición;
    }
}