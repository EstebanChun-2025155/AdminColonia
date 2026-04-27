package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Multa;
import com.Administracion.Colonia.Service.MultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MultaController {

    @Autowired
    private MultaService multaService;

    @GetMapping("/multa")
    public String mostrarMulta (Model model) {
        model.addAttribute("multas", multaService.getAllMulta());
        model.addAttribute("multaNueva", new Multa());
        model.addAttribute("panelActivo", "consultar");
        return "VistaMulta";
    }

    @GetMapping("/multa/nueva")
    public String nuevaMulta(Model model) {
        model.addAttribute("multas", multaService.getAllMulta());
        model.addAttribute("multaNueva", new Multa());
        model.addAttribute("panelActivo", "registrar");
        return "VistaMulta";
    }

    @PostMapping("/multa/nueva")
    public String guardarMulta(@ModelAttribute Multa multa) {
        multaService.saveMulta(multa);
        return "redirect:/multa";
    }

    @GetMapping("/multa/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("multas", multaService.getAllMulta());
        model.addAttribute("multaNueva", new Multa());
        model.addAttribute("multaEditar", multaService.getMultaById(id));
        model.addAttribute("panelActivo", "editar");  // ← ¿tienes esta línea?
        return "VistaMulta";
    }

    @PostMapping("/multa/editar/{id}")
    public String actualizarMulta(@PathVariable Integer id, @ModelAttribute Multa multa) {
        multaService.updateMulta(id, multa);
        return "redirect:/multa";
    }

    @GetMapping("/multa/eliminar/{id}")
    public String eliminarMulta(@PathVariable Integer id) {
        multaService.deleteMulta(id);
        return "redirect:/multa";
    }

}