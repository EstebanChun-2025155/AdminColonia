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

    @NotNull(message = "El id del residente es obligatorio")
    @Column (name = "id_Residente")
    private Integer idResidente;

    @NotBlank(message = "La clasificación del pago es obligatorio")
    @Pattern( regexp = "^(multa|mantenimiento|amenidad)$",
            message = "La clasificación del pago solo puede ser: multa, mantenimiento o amenidad")
    @Column (name = "clasificacion_Pago")
    private String clasificacionPago;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "75.01", message = "El monto debe ser mayor a 75.00")
    @Column (name = "monto")
    private Double monto;

    @NotNull(message = "La fecha de pago es obligatorio")
    @Column (name = "fecha_Pago")
    private LocalDate fechaPago;

    @NotBlank(message = "El método es obligatorio")
    @Pattern( regexp = "^(efectivo|transferencia|tarjeta)$",
            message = "El método del pago solo puede ser : efectivo, transferencia o tarjeta")
    @Column (name = "metodo")
    private String metodo;

    @NotBlank(message = "La referencia es obligatorio")
    @Size(min = 1, max = 6, message = "La referencia debe tener entre 1 y 6 caracteres")
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