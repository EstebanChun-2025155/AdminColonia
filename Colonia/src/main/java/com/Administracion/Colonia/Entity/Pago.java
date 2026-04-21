package com.Administracion.Colonia.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "Pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_Pago")
    private Integer idPago;

    @NotNull(message = "El campo no debe de estar vacio")
    @Column (name = "id_Residente")
    private Integer idResidente;

    @NotBlank(message = "El campo de texto no debe de estar vacio")
    @Pattern( regexp = "^(multa|mantenimiento|amenidad)$",
            message = "La clasificación del pago es valido unicamente bajo los dominios: multa, mantenimiento, amenidad")
    @Column (name = "clasificacion_Pago")
    private String clasificacionPago;

    @NotNull(message = "El campo no debe de estar vacio")
    @DecimalMin(value = "75.01", message = "El monto debe ser mayor a 75.00")
    @Column (name = "monto")
    private Double monto;

    @NotNull(message = "El campo no debe de estar vacio")
    @Column (name = "fecha_Pago")
    private LocalDate fechaPago;

    @NotBlank(message = "El campo de texto no debe de estar vacio")
    @Pattern( regexp = "^(efectivo|transferencia|tarjeta)$",
            message = "El método de pago es valido unicamente bajo los dominios: efectivo, transferencia, tarjeta")
    @Column (name = "metodo")
    private String metodo;

    @NotBlank(message = "El campo de texto no debe de estar vacio")
    @Size(max = 6, message = "La referencia no puede exceder 6 caracteres")
    @Size(min = 6, message = "La referencia no puede exceder menos de 6 caracteres")
    @Column (name = "referencia")
    private String referencia;

    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    public Integer getIdResidente() {
        return idResidente;
    }

    public void setIdResidente(Integer idResidente) {
        this.idResidente = idResidente;
    }

    public String getClasificacionPago() {
        return clasificacionPago;
    }

    public void setClasificacionPago(String clasificacionPago) {
        this.clasificacionPago = clasificacionPago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}