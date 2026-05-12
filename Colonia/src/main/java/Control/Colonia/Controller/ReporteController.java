package Control.Colonia.Controller;

import Control.Colonia.Entity.Reporte;
import Control.Colonia.Service.ReporteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/reporte")
    public String mostrarReporte(HttpSession session, Model model) {
        model.addAttribute("reportes", reporteService.getAllReporte());
        model.addAttribute("reporteNuevo", new Reporte());
        model.addAttribute("panelActivo", "consultar");

        String tipo = (String) session.getAttribute("tipoUsuario");

        if (tipo == null) {
            return "redirect:/login";
        }

        model.addAttribute("isResidente", tipo.equals("RESIDENTE"));
        model.addAttribute("isSeguridad", tipo.equals("SEGURIDAD"));

        return "VistaReporte";
    }

    @GetMapping("/reporte/nuevo")
    public String nuevoReporte(Model model) {
        model.addAttribute("reportes", reporteService.getAllReporte());
        model.addAttribute("reporteNuevo", new Reporte());
        model.addAttribute("panelActivo", "registrar");
        return "VistaReporte";
    }

    @PostMapping("/reporte/nuevo")
    public String guardarReporte(@Valid @ModelAttribute("reporteNuevo") Reporte reporte,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("reportes", reporteService.getAllReporte());
            model.addAttribute("panelActivo", "registrar");
            return "VistaReporte";
        }
        try {
            reporteService.saveReporte(reporte);
        } catch (RuntimeException e) {
            model.addAttribute("reportes", reporteService.getAllReporte());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("panelActivo", "registrar");
            return "VistaReporte";
        }
        return "redirect:/reporte";
    }

    @GetMapping("/reporte/editar/{id}")
    public String editarReporte(@PathVariable Integer id, Model model) {
        model.addAttribute("reportes", reporteService.getAllReporte());
        model.addAttribute("reporteNuevo", new Reporte());
        model.addAttribute("reporteEditar", reporteService.getReporteById(id));
        model.addAttribute("panelActivo", "editar");
        return "VistaReporte";
    }

    @PostMapping("/reporte/editar/{id}")
    public String actualizarReporte(@PathVariable Integer id,
                                    @Valid @ModelAttribute("reporteEditar") Reporte reporte,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            reporte.setIdReporte(id);
            model.addAttribute("reportes", reporteService.getAllReporte());
            model.addAttribute("reporteNuevo", new Reporte());
            model.addAttribute("panelActivo", "editar");
            return "VistaReporte";
        }
        try {
            reporteService.updateReporte(id, reporte);
        } catch (RuntimeException e) {
            reporte.setIdReporte(id);
            model.addAttribute("reportes", reporteService.getAllReporte());
            model.addAttribute("reporteNuevo", new Reporte());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("panelActivo", "editar");
            return "VistaReporte";
        }
        return "redirect:/reporte";
    }

    @GetMapping("/reporte/eliminar/{id}")
    public String eliminarReporte(@PathVariable Integer id) {
        reporteService.deleteReporte(id);
        return "redirect:/reporte";
    }

    @GetMapping("/reporte/buscar")
    public String buscarReporte(@RequestParam Integer id, Model model) {
        try {
            Reporte reporte = reporteService.getReporteById(id);
            model.addAttribute("reportes", List.of(reporte));
        } catch (RuntimeException e) {
            model.addAttribute("reportes", reporteService.getAllReporte());
            model.addAttribute("errorGeneral", e.getMessage());
        }

        model.addAttribute("reporteNuevo", new Reporte());
        model.addAttribute("panelActivo", "consultar");
        return "VistaReporte";
    }

    @GetMapping("/reporte/historial")
    public String historial(@RequestParam("id") Integer id, Model model) {

        List<Reporte> historial = reporteService.buscarPorReportado(id);

        model.addAttribute("historial", historial);
        model.addAttribute("idBuscado", id);

        if (historial.isEmpty()) {
            model.addAttribute("mensajeHistorial", "No existe ese residente o no tiene reportes");
        }

        model.addAttribute("reportes", reporteService.getAllReporte());
        model.addAttribute("panelActivo", "historial");
        model.addAttribute("reporteNuevo", new Reporte());
        return "VistaReporte";
    }
}