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
        model.addAttribute("residenteForm", new Residente());
        return "residente";
    }

    @PostMapping("/eliminar/residente/{id}")
    public String eliminar(@PathVariable Integer id){
        residenteService.deleteResidente(id);
        return "redirect:/residente";
    }

    @GetMapping("/nuevo/residente")
    public String nuevoResidente(Model model){
        model.addAttribute("residenteForm", new Residente());
        return "residente";
    }

    @PostMapping("/guardar/residente")
    public String guardarResidente(@ModelAttribute Residente residente){
        residenteService.saveResidente(residente);
        return "redirect:/residente";
    }

    @GetMapping("/editar/residente/{id}")
    public String editarResidente(@PathVariable Integer id, Model model){
        Residente residente = residenteService.getResidenteById(id);
        model.addAttribute("residente", residenteService.getAllResidente());
        model.addAttribute("residenteForm", residente);
        model.addAttribute("tab", "editar");
        return "residente";
    }

    @PostMapping("/actualizar/residente/{id}")
    public String actualizarResidente(@PathVariable Integer id, @ModelAttribute Residente residente){
        residenteService.updateResidente(id, residente);
        return "redirect:/residente";

    }

    @GetMapping("/buscar/residente")
    public String buscarResidente(@RequestParam Integer id, Model model){
        Residente residente = residenteService.getResidenteById(id);

        model.addAttribute("residente", List.of(residente));
        model.addAttribute("residenteForm", new Residente());

        return "residente";
    }
}