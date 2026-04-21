package com.Administracion.Colonia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Visita")
public class Visitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visita")
    private Integer id;


    @NotBlank(message = "El campo de texto no debe de estar vacio")
    @Column(name = "nombre_visita")
    private String nombreVisita;

    @NotBlank(message = "El campo de texto no debe de estar vacio")
    @Size(min = 11, max = 11, message = "El documento no puede exceder de 11 caracteres")
    @Column(name = "documento")
    private String documento;

    @NotBlank(message = "El campo de texto no debe de estar vacio")
    @Size(min = 8, max = 8, message = "La placa no puede exceder de 8 caracteres")
    @Column(name = "placa")
    private String placa;

    @NotBlank(message = "El campo de texto no debe de estar vacio")
    @Column(name = "motivo")
    private String motivo;

    @NotNull(message = "El campo no puede estar vacio")
    @Column(name = "id_casa")
    private Integer idCasa;

    //Se agregan Getters and Setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreVisita() {
        return nombreVisita;
    }

    public void setNombreVisita(String nombreVisita) {
        this.nombreVisita = nombreVisita;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(Integer idCasa) {
        this.idCasa = idCasa;
    }
}