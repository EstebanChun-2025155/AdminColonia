package Control.Colonia.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
@Table(name = "Reporte")
public class Reporte {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_Reporte")
    private Integer idReporte;

    @NotNull(message = "El ID del reportante es obligatorio")
    @Column (name = "id_Residente_Reportante")
    private Integer idResidenteReportante;

    @NotNull(message = "El ID del reportado es obligatorio")
    @Column (name = "id_Residente_Reportado")
    private Integer idResidenteReportado;

    @NotBlank(message = "El tipo de reporte es obligatorio")
    @Pattern(regexp = "^(queja|incidente|ruido|seguridad|otro)$",
            message = "El tipo de reporte solo puede ser: queja, incidente, ruido, seguridad u otro")
    @Column (name = "tipo_Reporte")
    private String tipoReporte;

    @NotBlank(message = "La descripción es obligatorio")
    @Column (name = "descripcion")
    private String descripcion;

    @NotNull(message = "La fecha del reporte es obligatorio")
    @Column (name = "fecha_reporte")
    private LocalDate fechaReporte;

    @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "^(pendiente|en_revision|resuelto|anulado)$",
            message = "El estado solo puede ser: pendiente, en_revision, resuelto o anulado")
    @Column (name = "estado")
    private String estado;

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public Integer getIdResidenteReportante() {
        return idResidenteReportante;
    }

    public void setIdResidenteReportante(Integer idResidenteReportante) {
        this.idResidenteReportante = idResidenteReportante;
    }

    public Integer getIdResidenteReportado() {
        return idResidenteReportado;
    }

    public void setIdResidenteReportado(Integer idResidenteReportado) {
        this.idResidenteReportado = idResidenteReportado;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(LocalDate fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
