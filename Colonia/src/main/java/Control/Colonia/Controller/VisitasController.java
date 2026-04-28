package Control.Colonia.Controller;

import Control.Colonia.Entity.Visitas;
import Control.Colonia.Service.VisitasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("/visitas")
public class VisitasController {

    private final VisitasService visitasService;

    public VisitasController(VisitasService visitasService) {
        this.visitasService = visitasService;
    }

    @GetMapping
    public String verVisitas(Model model) {
        model.addAttribute("visitas", visitasService.getAllVisitas());
        return "visitas";
    }

    @GetMapping("/buscar")
    public String buscarVisitas(@RequestParam("id") Integer id, Model model) {
        try {
            Visitas visitas = visitasService.getVisitasById(id);
            model.addAttribute("visitas", List.of(visitas));
        } catch (RuntimeException e) {
            model.addAttribute("visitas", List.of());
            model.addAttribute("error", "No se encontró la visita");
        }
        return "visitas";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("visitas") Visitas visitas,
                          RedirectAttributes redirect) {

        try {
            visitasService.saveVisitas(visitas);
        } catch (RuntimeException e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/visitas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id,
                         @RequestParam(value = "tab", required = false) String tab,
                         Model model) {

        model.addAttribute("visita", visitasService.getVisitasById(id));
        model.addAttribute("visitas", visitasService.getAllVisitas());
        model.addAttribute("modoEditar", true);

        model.addAttribute("tabActiva", tab);
        return "visitas";
    }

    @PostMapping("/actualizar")
    public String actualizarVisitas(@ModelAttribute Visitas visitas) {
        visitasService.saveVisitas(visitas);
        return "redirect:/visitas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        visitasService.deleteVisitas(id);
        return "redirect:/visitas";
    }
}