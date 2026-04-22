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
        List<Multa> multas = multaService.getAllMulta();
        model.addAttribute("multas", multas);
        return "VistaMulta";
    }

    @GetMapping("/multa/nueva")
    public String nuevaMulta(Model model) {
        model.addAttribute("multa", new Multa());
        return "FormularioMulta";
    }

    @GetMapping("/multa/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("multa", multaService.updateMulta(id);
        return "FormularioMulta";
    }

    @GetMapping("/multa/eliminar/{id}")
    public String eliminarMulta(@PathVariable Integer id) {
        multaService.deleteMulta(id);
        return "redirect:/multa";
    }

}