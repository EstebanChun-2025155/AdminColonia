package Control.Colonia.Controller;

import Control.Colonia.Entity.Visitas;
import Control.Colonia.Repository.CasaRepository;
import Control.Colonia.Service.VisitasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/visitas")
public class VisitasController {

    @Autowired
    private VisitasService visitasService;

    @Autowired
    private CasaRepository casaRepository;

    @GetMapping
    public String verVisitas(Model model) {

        model.addAttribute("visitas", visitasService.getAllVisitas());
        model.addAttribute("visitaForm", new Visitas());
        model.addAttribute("tabActiva", "consultar");

        return "visitas";
    }

    @GetMapping("/buscar")
    public String buscarVisitas(@RequestParam("id") Integer id, Model model) {

        try {
            Visitas visita = visitasService.getVisitasById(id);
            model.addAttribute("visitas", List.of(visita));
        } catch (RuntimeException e) {
            model.addAttribute("visitas", List.of());
            model.addAttribute("errorGeneral", "No se encontró la visita con ID: " + id);
        }

        model.addAttribute("visitaForm", new Visitas());
        model.addAttribute("tabActiva", "consultar");

        return "visitas";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("visitaForm") Visitas visita,
                          BindingResult result,
                          Model model) {

        if (result.hasErrors()) {
            model.addAttribute("visitas", visitasService.getAllVisitas());
            model.addAttribute("tabActiva", "registrar");
            return "visitas";
        }

        if (!casaRepository.existsById(visita.getIdCasa())){
            result.rejectValue("idCasa", "error.casa", "El ID de esta vivienda no Existe");

            model.addAttribute("visitas", visitasService.getAllVisitas());
            model.addAttribute("tabActiva", "registrar");
            return "visitas";
        }

        try {
            visitasService.saveVisitas(visita);
        } catch (RuntimeException e) {
            model.addAttribute("visitas", visitasService.getAllVisitas());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tabActiva", "registrar");
            return "visitas";
        }

        return "redirect:/visitas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        try {
            Visitas visita = visitasService.getVisitasById(id);

            model.addAttribute("visitaForm", visita);
            model.addAttribute("visitas", visitasService.getAllVisitas());
            model.addAttribute("tabActiva", "editar");

        } catch (RuntimeException e) {
            model.addAttribute("visitaForm", new Visitas());
            model.addAttribute("visitas", visitasService.getAllVisitas());
            model.addAttribute("errorGeneral", "No se encontró la visita con ID: " + id);
            model.addAttribute("tabActiva", "consultar");
        }

        return "visitas";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarVisitas(@PathVariable Integer id,
                                    @Valid @ModelAttribute("visitaForm") Visitas visita,
                                    BindingResult result,
                                    Model model) {

        if (result.hasErrors()) {
            visita.setId(id);

            model.addAttribute("visitaForm", visita);
            model.addAttribute("visitas", visitasService.getAllVisitas());
            model.addAttribute("tabActiva", "editar");

            return "visitas";
        }

        if (!casaRepository.existsById(visita.getIdCasa())){
            result.rejectValue("idCasa", "error.casa", "El ID de la vivienda no Existe");

            visita.setId(id);
            model.addAttribute("visitaForm", visita);
            model.addAttribute("visitas", visitasService.getAllVisitas());
            model.addAttribute("tabActiva", "editar");
            return "visitas";
        }

        try {
            visitasService.getVisitasById(id);

            visita.setId(id);
            visitasService.saveVisitas(visita);

        } catch (RuntimeException e) {
            visita.setId(id);

            model.addAttribute("visitaForm", visita);
            model.addAttribute("visitas", visitasService.getAllVisitas());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tabActiva", "editar");

            return "visitas";
        }

        return "redirect:/visitas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {

        visitasService.deleteVisitas(id);

        return "redirect:/visitas";
    }
}