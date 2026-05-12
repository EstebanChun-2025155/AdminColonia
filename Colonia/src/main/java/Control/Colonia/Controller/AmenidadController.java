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

    @GetMapping
    public String mostrarAmenidades(HttpSession session, Model model) {
        List<Amenidad> lista = amenidadService.getAllAmenidad();
        model.addAttribute("amenidades", lista);
        model.addAttribute("amenidadForm", new Amenidad());

        String tipo = (String) session.getAttribute("tipoUsuario");

        if (tipo == null) {
            return "redirect:/login";
        }

        model.addAttribute("isResidente", tipo.equals("RESIDENTE"));
        model.addAttribute("isSeguridad", tipo.equals("SEGURIDAD"));

        return "Amenidades";
    }

    @GetMapping("/nueva")
    public String nuevaAmenidad(Model model) {
        model.addAttribute("amenidades", amenidadService.getAllAmenidad());
        model.addAttribute("amenidadForm", new Amenidad());
        model.addAttribute("tab", "registrar");
        return "Amenidades";
    }

    // GUARDAR (CORREGIDO)
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("amenidadForm") Amenidad amenidad, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("amenidades", amenidadService.getAllAmenidad());
            model.addAttribute("tab", "registrar");
            return "Amenidades";
        }

        try {
            amenidadService.saveAmenidad(amenidad);
        } catch (RuntimeException e) {
            model.addAttribute("amenidades", amenidadService.getAllAmenidad());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "registrar");
            return "Amenidades";
        }

        return "redirect:/amenidades";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Amenidad amenidad = amenidadService.getAmenidadById(id);
        model.addAttribute("amenidades", amenidadService.getAllAmenidad());
        model.addAttribute("amenidadForm", amenidad);
        model.addAttribute("tab", "editar");
        return "Amenidades";
    }

    // ACTUALIZAR
    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id,
                             @Valid @ModelAttribute("amenidadForm") Amenidad amenidad,
                             BindingResult result,
                             Model model) {

        // 1. Verificar si hay errores de validación
        if (result.hasErrors()) {
            amenidad.setIdAmenidad(id); // Asegúrate que el objeto mantenga su ID
            model.addAttribute("amenidades", amenidadService.getAllAmenidad());
            model.addAttribute("tab", "editar");
            return "Amenidades";
        }

        try {
            amenidadService.updateAmenidad(id, amenidad);
        } catch (RuntimeException e) {
            amenidad.setIdAmenidad(id);
            model.addAttribute("amenidades", amenidadService.getAllAmenidad());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "editar");
            return "Amenidades";
        }

        return "redirect:/Amenidades";
    }

    // ELIMINAR
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        amenidadService.deleteAmenidad(id);
        return "redirect:/Amenidades";
    }

    // BUSCAR
    @GetMapping("/buscar")
    public String buscar(@RequestParam Integer id, Model model) {
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
}