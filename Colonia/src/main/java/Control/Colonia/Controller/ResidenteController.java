package Control.Colonia.Controller;

import Control.Colonia.Entity.Residente;
import Control.Colonia.Service.ResidenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class ResidenteController {
    @Autowired
    private ResidenteService residenteService;

    @GetMapping("/residente")
    public String mostrarResidente(Model model){
        List<Residente> lista = residenteService.getAllResidente();
        model.addAttribute("residente", lista);

        return "residente";
    }
}