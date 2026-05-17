package Control.Colonia.Controller;

import Control.Colonia.Entity.Amenidad;
import Control.Colonia.Service.AmenidadService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/amenidades")
public class AmenidadController {

    @Autowired
    private AmenidadService amenidadService;

    private boolean cargarDatos(HttpSession session, Model model){
        String tipo = (String) session.getAttribute("tipoUsuario");

        if (tipo == null){
            return false;
        }

        model.addAttribute("isResidente", tipo.equals("RESIDENTE"));
        model.addAttribute("isSeguridad", tipo.equals("SEGURIDAD"));

        if (tipo.equals("SEGURIDAD")){
            model.addAttribute("amenidades", amenidadService.getAllAmenidad());
        }

        if (tipo.equals("RESIDENTE")){
            Integer idResidente = (Integer) session.getAttribute("idResidente");

            if (idResidente == null){
                return false;
            }

            model.addAttribute("amenidades", amenidadService.buscarPorResidente(idResidente));
        }

        return true;
    }

    @GetMapping
    public String mostrarAmenidades(HttpSession session, Model model) {
        if (!cargarDatos(session, model)) {
            return "redirect:/login";
        }

        model.addAttribute("amenidadForm", new Amenidad());
        model.addAttribute("tab", "consultar");

        return "Amenidades";
    }

    @GetMapping("/nueva")
    public String nuevaAmenidad(HttpSession session, Model model) {
        if (!cargarDatos(session, model)){
            return "redirect:/login";
        }
        model.addAttribute("amenidadForm", new Amenidad());
        model.addAttribute("tab", "registrar");

        return "Amenidades";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("amenidadForm") Amenidad amenidad, BindingResult result, HttpSession session, Model model) {
        if (!cargarDatos(session, model)){
            return "redirect:/login";
        }

        if (result.hasErrors()) {
            model.addAttribute("tab", "registrar");
            return "Amenidades";
        }

        try {
            amenidadService.saveAmenidad(amenidad);
        } catch (RuntimeException e) {
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "registrar");
            return "Amenidades";
        }

        return "redirect:/amenidades";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, HttpSession session, Model model) {
       if (!cargarDatos(session, model)){
           return "redirect:/login";
       }

        Amenidad amenidad = amenidadService.getAmenidadById(id);

        model.addAttribute("amenidadForm", amenidad);
        model.addAttribute("tab", "editar");
        return "Amenidades";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @Valid @ModelAttribute("amenidadForm") Amenidad amenidad,
                             BindingResult result, HttpSession session, Model model) {

        if (!cargarDatos(session, model)){
            return "redirect:/login";
        }

        amenidad.setIdAmenidad(id);

        if (result.hasErrors()) {
           model.addAttribute("tab", "editar");
            return "Amenidades";
        }

        try {
            amenidadService.updateAmenidad(id, amenidad);
        } catch (RuntimeException e) {
            amenidad.setIdAmenidad(id);
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "editar");
            return "Amenidades";
        }

        return "redirect:/amenidades";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, HttpSession session) {
        String tipo = (String) session.getAttribute("tipoUsuario");

        if (tipo == null){
            return "redirect:/login";
        }

        amenidadService.deleteAmenidad(id);

        return "redirect:/amenidades";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam Integer id, HttpSession session, Model model) {
       if (!cargarDatos(session, model)){
           return "redirect:/login";
       }

        try {
           Amenidad amenidad = amenidadService.getAmenidadById(id);
           model.addAttribute("amenidades" , List.of(amenidad));
       } catch (Exception e) {
           model.addAttribute("amenidades", List.of());
           model.addAttribute("errorGeneral", "No se encontró la reservación con ID: " + id);
       }

       model.addAttribute("amenidadForm", new Amenidad());
       model.addAttribute("tab", "consultar");
       return "Amenidades";
    }

    @GetMapping("/residente")
    public String vistaAmenidadesResidente(HttpSession session, Model model) {
        if (!cargarDatos(session, model)){
            return "redirect:/login";
        }

        model.addAttribute("amenidadForm", new Amenidad());
        model.addAttribute("amenidadSeleccionada", null);
        model.addAttribute("mostrarFormulario", false);

        return "AmenidadesResidente";
    }

    @GetMapping("/residente/reservar")
    public String mostrarFormularioReserva(@RequestParam String nombre, HttpSession session, Model model) {

        if (!cargarDatos(session, model)){
            return "redirect:/login";
        }

        Amenidad amenidad = new Amenidad();

        Integer idResidente = (Integer) session.getAttribute("idResidente");

        amenidad.setIdResidente(idResidente);
        amenidad.setNombreAmenidad(nombre);
        amenidad.setEstado("pendiente");

        model.addAttribute("amenidadForm", amenidad);
        model.addAttribute("amenidadSeleccionada", nombre);
        model.addAttribute("mostrarFormulario", true);

        return "AmenidadesResidente";
    }

    @PostMapping("/residente/guardar/amenidad")
    public String guardarReservaResidente(@Valid @ModelAttribute("amenidadForm") Amenidad amenidad, BindingResult result, HttpSession session, Model model) {
        if (!cargarDatos(session, model)){
            return "redirect:/login";
        }

        Integer idResidente = (Integer) session.getAttribute("idResidente");

        amenidad.setIdResidente(idResidente);
        amenidad.setEstado("pendiente");

        if (result.hasErrors()) {
            model.addAttribute("amenidadSeleccionada", amenidad.getNombreAmenidad());
            model.addAttribute("mostrarFormulario", true);
            return "AmenidadesResidente";
        }

        try {
            amenidadService.saveAmenidad(amenidad);
        } catch (RuntimeException e) {
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("amenidadSeleccionada", amenidad.getNombreAmenidad());
            model.addAttribute("mostrarFormulario", true);
            return "AmenidadesResidente";
        }

        return "redirect:/amenidades/residente";
    }
}