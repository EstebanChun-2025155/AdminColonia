package com.Administracion.Colonia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
@Table (name = "Multa")
public class Multa {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_multa")
    private Integer idMulta;

    @NotNull(message = "El campo no debe de estar vacio")
    @DecimalMin(value = "120.01", message = "El precio debe ser mayor a 120.00")
    @Column (name = "monto")
    private Double monto;

    @NotBlank(message = "El campo de texto no debe de estar vacio")
    @Column (name = "descripcion")
    private String descripcion;

    @NotNull(message = "El campo no debe de estar vacio")
    @Column (name = "fecha_emision")
    private LocalDate fechaEmision;

    @NotBlank(message = "El campo de texto no debe de estar vacio")
    @Pattern( regexp  = "^(pagado|pendiente|anulada)$",
            message =  "El estado es valido unicamente bajo los dominios: pagado, pendiente, anulada")
    @Column (name = "estado")
    private String estado;

    @NotBlank(message = "El campo de texto no debe de estar vacio")
    @Pattern( regexp  = "^(visita|residente)$",
            message =  "El estado es valido unicamente bajo los dominios: visita, residente")
    @Column (name = "tipo_persona")
    private String tipoPersona;

    public Integer getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(Integer idMulta) {
        this.idMulta = idMulta;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
}