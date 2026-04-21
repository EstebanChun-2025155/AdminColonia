package Control.Colonia.Controller;

import Control.Colonia.Entity.Casa;
import Control.Colonia.Service.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
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

        return "casa";
    }
}