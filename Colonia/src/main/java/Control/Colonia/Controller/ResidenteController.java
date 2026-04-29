package Control.Colonia.Controller;

import Control.Colonia.Entity.Residente;
import Control.Colonia.Service.ResidenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("residente", residenteService.getAllResidente());
        model.addAttribute("residenteForm", new Residente());
        model.addAttribute("tab", "registrar");

        return "residente";
    }

    @PostMapping("/guardar/residente")
    public String guardarResidente(@Valid @ModelAttribute("residenteForm") Residente residente, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("residente", residenteService.getAllResidente());
            model.addAttribute("tab", "registrar");
            return "residente";
        }

        try {
            residenteService.saveResidente(residente);
        } catch (RuntimeException e) {
            model.addAttribute("residente", residenteService.getAllResidente());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "registrar");
            return "residente";
        }
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
    public String actualizarResidente(@PathVariable Integer id, @Valid @ModelAttribute("residenteForm") Residente residente, BindingResult result, Model model){
        if (result.hasErrors()){
            residente.setIdResidente(id);
            model.addAttribute("residente", residenteService.getAllResidente());
            model.addAttribute("tab", "editar");
            return "residente";
        }

        try {
            residenteService.updateResidente(id, residente);
        } catch (RuntimeException e) {
            residente.setIdResidente(id);
            model.addAttribute("residente", residenteService.getAllResidente());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "editar");
            return "residente";
        }
        return "redirect:/residente";

    }

    @GetMapping("/buscar/residente")
    public String buscarResidente(@RequestParam Integer id, Model model){
        Residente residente = residenteService.getResidenteById(id);

        model.addAttribute("residente", List.of(residente));
        model.addAttribute("residenteForm", new Residente());
        model.addAttribute("tab", "consultar");

        return "residente";
    }
}