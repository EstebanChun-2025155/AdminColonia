package Control.Colonia.Controller;

import Control.Colonia.Entity.Reporte;
import Control.Colonia.Repository.ResidenteRepository;
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

    @Autowired
    private ResidenteRepository residenteRepository;

    private boolean cargarDatos(HttpSession session, Model model){
        String tipo = (String) session.getAttribute("tipoUsuario");

        if (tipo == null){
            return false;
        }

        model.addAttribute("isResidente", tipo.equals("RESIDENTE"));
        model.addAttribute("isSeguridad", tipo.equals("SEGURIDAD"));

        if (tipo.equals("SEGURIDAD")){
            model.addAttribute("reportes", reporteService.getAllReporte());
        }

        if (tipo.equals("RESIDENTE")){
            Integer idResidente = (Integer) session.getAttribute("idResidente");
            if (idResidente == null){
                return false;
            }
            model.addAttribute("reportes", reporteService.buscarporReportante(idResidente));
        }

        return true;
    }

    @GetMapping("/reporte")
    public String mostrarReporte(HttpSession session, Model model) {
        if (!cargarDatos(session, model)) {
            return "redirect:/login";
        }

        String tipo = (String) session.getAttribute("tipoUsuario");
        Reporte reporte = new Reporte();

        if ("RESIDENTE".equals(tipo)){
            Integer idResidente = (Integer) session.getAttribute("idResidente");

            if (idResidente == null){
                return "redirect:/login";
            }

            reporte.setIdResidenteReportante(idResidente);
        }

        model.addAttribute("reporteNuevo", reporte);
        model.addAttribute("panelActivo", "consultar");

        return "VistaReporte";
    }

    @GetMapping("/reporte/nuevo")
    public String nuevoReporte(HttpSession session, Model model) {
        if (!cargarDatos(session, model)) {
            return "redirect:/login";
        }

        String tipo = (String) session.getAttribute("tipoUsuario");
        Reporte reporte = new Reporte();

        if ("RESIDENTE".equals(tipo)){
            Integer idResidente = (Integer) session.getAttribute("idResidente");

            if (idResidente == null){
                return "redirect:/login";
            }

            reporte.setIdResidenteReportante(idResidente);
        }

        model.addAttribute("reporteNuevo", reporte);
        model.addAttribute("panelActivo", "registrar");

        return "VistaReporte";
    }

    @PostMapping("/reporte/nuevo")
    public String guardarReporte(@Valid @ModelAttribute("reporteNuevo") Reporte reporte, BindingResult result, HttpSession session, Model model) {
        if (!cargarDatos(session, model)) {
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            model.addAttribute("panelActivo", "registrar");
            return "VistaReporte";
        }

        if (!residenteRepository.existsById(reporte.getIdResidenteReportante())){
            result.rejectValue("idResidenteReportante", "error.residente", "El ID del residente reportante no existe");

            model.addAttribute("panelActivo", "registrar");

            return "VistaReporte";
        }

        if (!residenteRepository.existsById(reporte.getIdResidenteReportado())){
            result.rejectValue("idResidenteReportado", "error.residente", "El ID del residente reportado no existe");

            model.addAttribute("panelActivo", "registrar");

            return "VistaReporte";
        }

        try {
            reporteService.saveReporte(reporte);
        } catch (RuntimeException e) {
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("panelActivo", "registrar");
            return "VistaReporte";
        }
        return "redirect:/reporte";
    }

    @GetMapping("/reporte/editar/{id}")
    public String editarReporte(@PathVariable Integer id, HttpSession session, Model model) {
        if (!cargarDatos(session, model)) {
            return "redirect:/login";
        }

        String tipo = (String) session.getAttribute("tipoUsuario");

        if (!tipo.equals("SEGURIDAD")){
            return "redirect:/reporte";
        }

        model.addAttribute("reporteNuevo", new Reporte());
        model.addAttribute("reporteEditar", reporteService.getReporteById(id));

        model.addAttribute("panelActivo", "editar");

        return "VistaReporte";
    }

    @PostMapping("/reporte/editar/{id}")
    public String actualizarReporte(@PathVariable Integer id, @Valid @ModelAttribute("reporteEditar") Reporte reporte, BindingResult result,  HttpSession session, Model model) {
        if (!cargarDatos(session, model)) {
            return "redirect:/login";
        }

        String tipo = (String) session.getAttribute("tipoUsuario");

        if (!tipo.equals("SEGURIDAD")){
            return "redirect:/reporte";
        }

        if (result.hasErrors()) {
            reporte.setIdReporte(id);
            model.addAttribute("reporteNuevo", new Reporte());
            model.addAttribute("panelActivo", "editar");
            return "VistaReporte";
        }

        if (!residenteRepository.existsById(reporte.getIdReporte())){
            result.rejectValue("idResidenteReportante", "error.residente", "El ID del residente reportante no existe");

            reporte.setIdReporte(id);
            model.addAttribute("reporteNuevo", new Reporte());
            model.addAttribute("panelActivo", "editar");

            return "VistaReporte";
        }


        if (!residenteRepository.existsById(reporte.getIdReporte())){
            result.rejectValue("idResidenteReportado", "error.residente", "El ID del residente reportado no existe");

            reporte.setIdReporte(id);
            model.addAttribute("reporteNuevo", new Reporte());
            model.addAttribute("panelActivo", "editar");

            return "VistaReporte";
        }

        try {
            reporteService.updateReporte(id, reporte);
        } catch (RuntimeException e) {
            reporte.setIdReporte(id);
            model.addAttribute("reporteNuevo", new Reporte());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("panelActivo", "editar");
            return "VistaReporte";
        }
        return "redirect:/reporte";
    }

    @PostMapping("/reporte/eliminar/{id}")
    public String eliminarReporte(@PathVariable Integer id, HttpSession session) {
       String tipo = (String) session.getAttribute("tipoUsuario");

       if (tipo == null){
           return "redirect:/login";
       }

       if (!tipo.equals("SEGURIDAD")){
           return "redirect:/reporte";
       }

        reporteService.deleteReporte(id);
        return "redirect:/reporte";
    }

    @GetMapping("/reporte/buscar")
    public String buscarReporte(@RequestParam Integer id, HttpSession session, Model model) {
        if (!cargarDatos(session, model)) {
            return "redirect:/login";
        }

        String tipo = (String) session.getAttribute("tipoUsuario");

        if (!tipo.equals("SEGURIDAD")){
            return "redirect:/reporte";
        }

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
    public String historial(@RequestParam("id") Integer id,HttpSession session, Model model) {
        if (!cargarDatos(session, model)) {
            return "redirect:/login";
        }

        String tipo = (String) session.getAttribute("tipoUsuario");

        if (!tipo.equals("SEGURIDAD")){
            return "redirect:/reporte";
        }

        List<Reporte> historial = reporteService.buscarPorReportado(id);

        model.addAttribute("historial", historial);
        model.addAttribute("idBuscado", id);

        if (historial.isEmpty()) {
            model.addAttribute("mensajeHistorial", "No existe ese residente o no tiene reportes");
        }

        model.addAttribute("panelActivo", "historial");
        model.addAttribute("reporteNuevo", new Reporte());
        return "VistaReporte";
    }
}