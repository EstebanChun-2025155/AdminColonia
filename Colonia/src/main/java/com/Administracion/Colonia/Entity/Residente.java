package com.Administracion.Colonia.Entity;


import jakarta.persistence.*;

@Entity
@Table (name = "Residente")
public class Residente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_Residente")
    private Integer idResidente;

    @Column (name = "nombre_Residente")
    private String nombreResidente;

    @Column (name = "dpi_Residente")
    private String dpiResidente;

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