package Control.Colonia.Controller;

import Control.Colonia.Entity.Accesos;
import Control.Colonia.Service.AccesosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/accesos")
public class AccesosController {

    @Autowired
    private AccesosService accesosService;

    @GetMapping
    public String verAccesos(Model model) {

        model.addAttribute("accesos", accesosService.getAllAccesos());
        model.addAttribute("acceso", new Accesos());

        return "Accesos";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("acceso") Accesos acceso,
                          RedirectAttributes redirect) {

        try {
            accesosService.saveAcceso(acceso);
        } catch (RuntimeException e) {
            redirect.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/accesos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("acceso", accesosService.getAccesosById(id));
        model.addAttribute("accesos", accesosService.getAllAccesos());
        return "Accesos";
    }

    @PostMapping("/actualizar")
    public String actualizarAcceso(@ModelAttribute Accesos acceso) {
        accesosService.saveAcceso(acceso);
        return "redirect:/accesos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        accesosService.deleteAcceso(id);
        return "redirect:/accesos";
    }
}