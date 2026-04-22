package Control.Colonia.Controller;

import Control.Colonia.Entity.Casa;
import Control.Colonia.Service.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class CasaController {
    @Autowired
    private CasaService casaService;

    @GetMapping("/casa")
    public String mostrarCasa(Model model){
        List<Casa> lista = casaService.getAllCasa();
        model.addAttribute("casa", lista);
        model.addAttribute("casaForm", new Casa());
        return "casa";
    }

    @PostMapping("/eliminar/casa/{id}")
    public String eliminar(@PathVariable Integer id){
        casaService.deleteCasa(id);
        return "redirect:/casa";
    }

    @GetMapping("/nueva/casa")
    public String nuevaCasa(Model model){
        model.addAttribute("casaForm", new Casa());
        return "casa";
    }

    @PostMapping("/guardar/casa")
    public String guardar(@ModelAttribute Casa casa){
        casaService.saveCasa(casa);
        return "redirect:/casa";
    }

    @GetMapping("/editar/casa/{id}")
    public String editar(@PathVariable Integer id, Model model){
        Casa casa = casaService.getCasaById(id);
        model.addAttribute("casa", casaService.getAllCasa()); // lista
        model.addAttribute("casaForm", casa);
        model.addAttribute("tab", "editar");
        return "casa";
    }

    @PostMapping("/actualizar/casa/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute Casa casa){
        casaService.updateCasa(id, casa);
        return "redirect:/casa";

    }
}