package Control.Colonia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Casa")
public class Casa {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column (name = "id_casa")
    private Integer idCasa;

    @NotBlank(message = "El número de casa es obligatorio")
    @Size(min = 1, max = 5, message = "El número de casa debe tener entre 1 y 5 caracteres")
    @Column (name = "no_de_casa")
    private String noDeCasa;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 25, min = 15, message = "La dirección debe tener entre 15 y 25 caracteres")
    @Column(name = "Direccion")
    private String direccion;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern( regexp  = "^(?i)(ocupada|disponible|mantenimiento)$",
            message =  "El estado solo puede ser: ocupada, disponible o mantenimiento")
    @Column (name = "estado", nullable = false)
    private String estado;

    @NotBlank(message = "El campo de texto no debe de estar vacios")
    @Column (name = "propietario")
    private String propietario;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "250000.00", message = "El precio debe ser mayor a 250,000.00")
    @Column (name = "precio_casa")
    private Double precioCasa;

    public Integer getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(Integer idCasa) {
        this.idCasa = idCasa;
    }

    public String getNoDeCasa() {
        return noDeCasa;
    }

    public void setNoDeCasa(String noDeCasa) {
        this.noDeCasa = noDeCasa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public Double getPrecioCasa() {
        return precioCasa;
    }

    public void setPrecioCasa(Double precioCasa) {
        this.precioCasa = precioCasa;
    }
}