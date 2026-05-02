package Control.Colonia.Controller;

import Control.Colonia.Entity.Casa;
import Control.Colonia.Service.CasaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CasaController {

    @Autowired
    private CasaService casaService;

    @GetMapping("/casa")
    public String mostrarCasa(Model model) {
        List<Casa> lista = casaService.getAllCasa();
        model.addAttribute("casa", lista);
        model.addAttribute("casaForm", new Casa());

        return "casa";
    }

    @PostMapping("/eliminar/casa/{id}")
    public String eliminar(@PathVariable Integer id) {
        casaService.deleteCasa(id);
        return "redirect:/casa";
    }

    @GetMapping("/nueva/casa")
    public String nuevaCasa(Model model) {
        model.addAttribute("casa", casaService.getAllCasa());
        model.addAttribute("casaForm", new Casa());
        model.addAttribute("tab", "registrar");

        return "casa";
    }

    @PostMapping("/guardar/casa")
    public String guardar(@Valid @ModelAttribute("casaForm") Casa casa, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("casa", casaService.getAllCasa());
            model.addAttribute("tab", "registrar");
            return "casa";
        }

        try {
            casaService.saveCasa(casa);
        } catch (RuntimeException e) {
            model.addAttribute("casa", casaService.getAllCasa());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "registrar");
            return "casa";
        }

        return "redirect:/casa";
    }

    @GetMapping("/editar/casa/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Casa casa = casaService.getCasaById(id);

        model.addAttribute("casa", casaService.getAllCasa());
        model.addAttribute("casaForm", casa);
        model.addAttribute("tab", "editar");

        return "casa";
    }

    @PostMapping("/actualizar/casa/{id}")
    public String actualizar(@PathVariable Integer id, @Valid @ModelAttribute("casaForm") Casa casa, BindingResult result, Model model) {

        if (result.hasErrors()) {
            casa.setIdCasa(id);
            model.addAttribute("casa", casaService.getAllCasa());
            model.addAttribute("tab", "editar");
            return "casa";
        }

        try {
            casaService.updateCasa(id, casa);
        } catch (RuntimeException e) {
            casa.setIdCasa(id);
            model.addAttribute("casa", casaService.getAllCasa());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "editar");
            return "casa";
        }

        return "redirect:/casa";
    }

    @GetMapping("/buscar/casa")
    public String buscarCasa(@RequestParam Integer id, Model model) {
        Casa casa = casaService.getCasaById(id);

        model.addAttribute("casa", List.of(casa));
        model.addAttribute("casaForm", new Casa());
        model.addAttribute("tab", "consultar");

        return "casa";
    }
}