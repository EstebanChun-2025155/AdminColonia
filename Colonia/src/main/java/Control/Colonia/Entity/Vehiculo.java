package Control.Colonia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name ="Vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Vehiculo")
    private Integer idVehiculo;

    @NotBlank(message = "Debe agregar la placa")
    @Pattern(regexp = "^[A-Za-z0-9-]{8}$", message = "La placa solo puede contener letras, números o guion")
    @Column(name = "placa", unique = true)
    private String placa;

    @NotBlank(message = "Debe llenar el campo")
    @Column(name = "marca_Modelo")
    private String marcaModelo;

    @NotBlank(message = "Debe llenar el campo")
    @Column(name = "color")
    private String color;

    @NotBlank(message = "Debe llenar el campo")
    @Column(name = "propietario")
    private String propietario;

    @NotNull(message = "La casa no puede ser nula")
    @Column(name = "id_Casa", nullable = false)
    private Integer idCasa;

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarcaModelo() {
        return marcaModelo;
    }

    public void setMarcaModelo(String marcaModelo) {
        this.marcaModelo = marcaModelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public Integer getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(Integer idCasa) {
        this.idCasa = idCasa;
    }
}