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

    @NotBlank(message = "El campo de texto no debe de estar vacios")
    @Size(max = 5, message = "El no de Casa no puede exceder 5 caracteres")
    @Column (name = "no_de_casa")
    private String noDeCasa;

    @NotBlank(message = "El campo de texto no debe de estar vacios")
    @Size(max = 25, min = 15, message = "La dirección debe tener entre 10 y 25 caracteres")
    @Column(name = "Direccion")
    private String direccion;

    @NotBlank(message = "El campo de texto no debe de estar vacios")
    @Pattern( regexp  = "^(?i)(ocupada|disponible|mantenimiento)$",
            message =  "El estado es valido unicamente bajo los dominios: ocupada, disponible, mantenimiento")
    @Column (name = "estado", nullable = false)
    private String estado;

    @NotBlank(message = "El campo de texto no debe de estar vacios")
    @Column (name = "propietario")
    private String propietario;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "250000.00", message = "El precio debe ser mayor a 250,000.00")
    @Column (name = "precio_casa")
    private double precioCasa;

    public Integer getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(Integer idCasa) {
        this.idCasa = idCasa;
    }

    public String getNoDeCasa() {
        return noDeCasa;
    }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public void setNoDeCasa(String noDeCasa) { this.noDeCasa = noDeCasa; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public String getPropietario() { return propietario; }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public double getPrecioCasa() {
        return precioCasa;
    }

    public void setPrecioCasa(double precioCasa) {
        this.precioCasa = precioCasa;
    }
}