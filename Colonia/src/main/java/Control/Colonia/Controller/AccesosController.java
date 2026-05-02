package Control.Colonia.Controller;

import Control.Colonia.Entity.Accesos;
import Control.Colonia.Service.AccesosService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/accesos")
public class AccesosController {

    private final AccesosService accesosService;

    public AccesosController(AccesosService accesosService) {
        this.accesosService = accesosService;
    }

    @GetMapping
    public String verAccesos(Model model) {

        model.addAttribute("accesos", accesosService.getAllAccesos());
        model.addAttribute("acceso", new Accesos());
        model.addAttribute("tabActiva", "consultar");

        return "Accesos";
    }

    @GetMapping("/buscar")
    public String buscarAcceso(@RequestParam("id") Integer id, Model model) {

        try {
            Accesos acceso = accesosService.getAccesosById(id);
            model.addAttribute("accesos", List.of(acceso));
        } catch (RuntimeException e) {
            model.addAttribute("accesos", List.of());
            model.addAttribute("error", "No se encontró el acceso con ID: " + id);
        }

        model.addAttribute("acceso", new Accesos());
        model.addAttribute("tabActiva", "consultar");

        return "Accesos";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("acceso") Accesos acceso,
                          BindingResult result,
                          Model model) {

        if (result.hasErrors()) {
            model.addAttribute("accesos", accesosService.getAllAccesos());
            model.addAttribute("tabActiva", "registrar");
            return "Accesos";
        }

        try {
            accesosService.saveAcceso(acceso);
        } catch (RuntimeException e) {
            model.addAttribute("accesos", accesosService.getAllAccesos());
            model.addAttribute("error", e.getMessage());
            model.addAttribute("tabActiva", "registrar");
            return "Accesos";
        }

        return "redirect:/accesos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {

        try {
            Accesos acceso = accesosService.getAccesosById(id);

            model.addAttribute("acceso", acceso);
            model.addAttribute("accesos", accesosService.getAllAccesos());
            model.addAttribute("tabActiva", "editar");

        } catch (RuntimeException e) {
            model.addAttribute("acceso", new Accesos());
            model.addAttribute("accesos", accesosService.getAllAccesos());
            model.addAttribute("error", "No se encontró el acceso con ID: " + id);
            model.addAttribute("tabActiva", "consultar");
        }

        return "Accesos";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarAcceso(@PathVariable Integer id,
                                   @Valid @ModelAttribute("acceso") Accesos acceso,
                                   BindingResult result,
                                   Model model) {

        if (result.hasErrors()) {
            acceso.setId(id);

            model.addAttribute("acceso", acceso);
            model.addAttribute("accesos", accesosService.getAllAccesos());
            model.addAttribute("tabActiva", "editar");

            return "Accesos";
        }

        try {
            accesosService.updateAcceso(id, acceso);
        } catch (RuntimeException e) {
            acceso.setId(id);

            model.addAttribute("acceso", acceso);
            model.addAttribute("accesos", accesosService.getAllAccesos());
            model.addAttribute("error", e.getMessage());
            model.addAttribute("tabActiva", "editar");

            return "Accesos";
        }

        return "redirect:/accesos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {

        accesosService.deleteAcceso(id);

        return "redirect:/accesos";
    }
}