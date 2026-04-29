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

    @NotBlank(message = "El nombre del residente es obligatorio")
    @Column(name = "nombre_Residente")
    private String nombreResidente;

    @NotBlank(message = "El DPI es obligatorio")
    @Pattern(regexp = "^[0-9]{13}$", message = "El DPI debe contener exactamente 13 números")
    @Column(name = "dpi_Residente")
    private String dpiResidente;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{8}$", message = "El telefono debe contener exactamente 8 numeros")
    @Column(name = "telefono_Residente")
    private String telefonoResidente;

    @NotBlank(message = "La posición es obligatoria")
    @Pattern(regexp = "^(activo|inactivo)$",
            message = "La posicion solo es valida bajo los dominios: activo, inactivo")
    @Column(name = "posicion")
    private String posicion;

    @NotNull(message = "Los campos no pueden estar vacios")
    @Positive(message = "El ID de casa debe ser un número positivo")
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