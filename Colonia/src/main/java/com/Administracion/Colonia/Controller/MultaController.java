package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Multa;
import com.Administracion.Colonia.Service.MultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String guardarMulta(@Valid @ModelAttribute("multaNueva") Multa multa,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("multas", multaService.getAllMulta());
            model.addAttribute("panelActivo", "registrar");
            return "VistaMulta";
        }
        try {
            multaService.saveMulta(multa);
        } catch (RuntimeException e) {
            model.addAttribute("multas", multaService.getAllMulta());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("panelActivo", "registrar");
            return "VistaMulta";
        }
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
    public String actualizarMulta(@PathVariable Integer id,
                                  @Valid @ModelAttribute("multaEditar") Multa multa,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            multa.setIdMulta(id);
            model.addAttribute("multas", multaService.getAllMulta());
            model.addAttribute("multaNueva", new Multa());
            model.addAttribute("panelActivo", "editar");
            return "VistaMulta";
        }
        try {
            multaService.updateMulta(id, multa);
        } catch (RuntimeException e) {
            multa.setIdMulta(id);
            model.addAttribute("multas", multaService.getAllMulta());
            model.addAttribute("multaNueva", new Multa());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("panelActivo", "editar");
            return "VistaMulta";
        }
        return "redirect:/multa";
    }

    @GetMapping("/multa/eliminar/{id}")
    public String eliminarMulta(@PathVariable Integer id) {
        multaService.deleteMulta(id);
        return "redirect:/multa";
    }

    @GetMapping("/multa/buscar")
    public String buscarMulta(@RequestParam Integer id, Model model) {
        Multa multa = multaService.getMultaById(id);
        model.addAttribute("multas", List.of(multa));
        model.addAttribute("multaNueva", new Multa());
        model.addAttribute("panelActivo", "consultar");
        return "VistaMulta";
    }

}