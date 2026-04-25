package Control.Colonia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Residente")
public class Residente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_Residente")
    private Integer idResidente;

    @NotBlank(message = "Los campos no pueden estar vacios")
    @Column(name = "nombre_Residente")
    private String nombreResidente;

    @NotBlank(message = "Los campos no pueden estar vacios")
    @Size(min = 13, max = 13, message = "El DPI debe tener exactamente 13 caracteres")
    @Column(name = "dpi_Residente")
    private String dpiResidente;

    @NotBlank(message = "Los campos no pueden estar vacios")
    @Size(min = 8, max = 8, message = "El telefono debe tener exactamente 8 caracteres")
    @Pattern(regexp = "^[0-9]{8}$", message = "El telefono debe contener exactamente 8 numeros")
    @Column(name = "telefono_Residente")
    private String telefonoResidente;

    @NotBlank(message = "Los campos no pueden estar vacios")
    @Pattern(regexp = "^(activo|inactivo)$",
            message = "La posicion solo es valida bajo los dominios: activo, inactivo")
    @Column(name = "posicion")
    private String posicion;

    @NotNull(message = "Los campos no pueden estar vacios")
    @Column(name = "id_Casa")
    private Integer idCasa;

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

    public String getTelefonoResidente() {
        return telefonoResidente;
    }

    public void setTelefonoResidente(String telefonoResidente) {
        this.telefonoResidente = telefonoResidente;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Integer getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(Integer idCasa) {
        this.idCasa = idCasa;
    }
}