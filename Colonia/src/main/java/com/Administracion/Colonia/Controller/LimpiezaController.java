package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Limpieza;
import com.Administracion.Colonia.Service.LimpiezaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class LimpiezaController {
    @Autowired
    private LimpiezaService limpiezaService;

    @GetMapping("/limpieza")
    public String mostrarLimpieza(Model model){
        List<Limpieza> lista = limpiezaService.getAllLimpieza();
        model.addAttribute("limpieza", lista);
        model.addAttribute("limpiezaForm", new Limpieza());
        return "limpieza";
    }

    @PostMapping("/eliminar/limpieza/{id}")
    public String eliminar(@PathVariable Integer id){
        limpiezaService.deleteLimpieza(id);
        return "redirect:/limpieza";
    }

    @GetMapping("/nueva/limpieza")
    public String nuevaLimpieza(Model model){
        model.addAttribute("limpiezaForm", new Limpieza());
        return "limpieza";
    }

    @PostMapping("/guardar/limpieza")
    public String guardar(@ModelAttribute Limpieza limpieza){
        limpiezaService.saveLimpieza(limpieza);
        return "redirect:/limpieza";
    }

    @GetMapping("/editar/limpieza/{id}")
    public String editar(@PathVariable Integer id, Model model){
        Limpieza limpieza = limpiezaService.getLimpiezaById(id);
        model.addAttribute("limpieza", limpiezaService.getAllLimpieza());
        model.addAttribute("limpiezaForm", limpieza);
        model.addAttribute("tab", "editar");
        return "limpieza";
    }

    @PostMapping("/actualizar/limpieza/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute Limpieza limpieza){
        limpiezaService.updateLimpieza(id, limpieza);
        return "redirect:/limpieza";

    }

    @GetMapping("/buscar/limpieza")
    public String buscarLimpieza(@RequestParam Integer id, Model model){
        Limpieza limpieza = limpiezaService.getLimpiezaById(id);

        model.addAttribute("limpieza", List.of(limpieza));
        model.addAttribute("limpiezaForm", new Limpieza());

        return "limpieza";
    }
}