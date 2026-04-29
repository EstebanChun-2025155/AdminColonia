package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Limpieza;
import com.Administracion.Colonia.Service.LimpiezaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class LimpiezaController {

    @Autowired
    private LimpiezaService limpiezaService;

    @GetMapping("/limpieza")
    public String mostrarLimpieza(Model model) {
        List<Limpieza> lista = limpiezaService.getAllLimpieza();
        model.addAttribute("limpieza", lista);
        model.addAttribute("limpiezaForm", new Limpieza());

        return "limpieza";
    }

    @PostMapping("/eliminar/limpieza/{id}")
    public String eliminar(@PathVariable Integer id) {
        limpiezaService.deleteLimpieza(id);
        return "redirect:/limpieza";
    }

    @GetMapping("/nueva/limpieza")
    public String nuevaLimpieza(Model model) {
        model.addAttribute("limpieza", limpiezaService.getAllLimpieza());
        model.addAttribute("limpiezaForm", new Limpieza());
        model.addAttribute("tab", "registrar");

        return "limpieza";
    }

    @PostMapping("/guardar/limpieza")
    public String guardarLimpieza(@Valid @ModelAttribute("limpiezaForm") Limpieza limpieza, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("limpieza", limpiezaService.getAllLimpieza());
            model.addAttribute("tab", "registrar");
            return "limpieza";
        }

        try {
            limpiezaService.saveLimpieza(limpieza);
        } catch (RuntimeException e) {
            model.addAttribute("limpieza", limpiezaService.getAllLimpieza());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "registrar");
            return "limpieza";
        }
        return "redirect:/limpieza";
    }

    @GetMapping("/editar/limpieza/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Limpieza limpieza = limpiezaService.getLimpiezaById(id);

        model.addAttribute("limpieza", limpiezaService.getAllLimpieza());
        model.addAttribute("limpiezaForm", limpieza);
        model.addAttribute("tab", "editar");

        return "limpieza";
    }

    @PostMapping("/actualizar/limpieza/{id}")
    public String actualizarLimpieza(@PathVariable Integer id, @Valid @ModelAttribute("limpiezaForm") Limpieza limpieza, BindingResult result, Model model) {
        if (result.hasErrors()) {
            limpieza.setIdLimpieza(id);
            model.addAttribute("limpieza", limpiezaService.getAllLimpieza());
            model.addAttribute("tab", "editar");
            return "limpieza";
        }

        try {
            limpiezaService.updateLimpieza(id, limpieza);
        } catch (RuntimeException e) {
            limpieza.setIdLimpieza(id);
            model.addAttribute("limpieza", limpiezaService.getAllLimpieza());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "editar");
            return "limpieza";
        }
        return "redirect:/limpieza";
    }

    @GetMapping("/buscar/limpieza")
    public String buscarLimpieza(@RequestParam Integer id, Model model) {
        Limpieza limpieza = limpiezaService.getLimpiezaById(id);
        model.addAttribute("limpieza", List.of(limpieza));
        model.addAttribute("limpiezaForm", new Limpieza());
        model.addAttribute("tab", "consultar");
        
        return "limpieza";
    }
}