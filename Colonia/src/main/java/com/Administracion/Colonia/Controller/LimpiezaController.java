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
    public String mostrarLimpieza(Model model) {
        model.addAttribute("listaLimpieza", limpiezaService.getAllLimpieza());
        model.addAttribute("limpiezaForm", new Limpieza());
        return "VistaLimpieza";
    }

    // Ruta corregida para eliminar
    @PostMapping("/limpieza/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        limpiezaService.deleteLimpieza(id);
        return "redirect:/limpieza";
    }

    // Ruta corregida para guardar
    @PostMapping("/limpieza/guardar")
    public String guardar(@ModelAttribute Limpieza limpieza) {
        limpiezaService.saveLimpieza(limpieza);
        return "redirect:/limpieza";
    }

    @GetMapping("/limpieza/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Limpieza limpieza = limpiezaService.getLimpiezaById(id);
        model.addAttribute("listaLimpieza", limpiezaService.getAllLimpieza());
        model.addAttribute("limpiezaForm", limpieza);
        model.addAttribute("tab", "editar");
        return "VistaLimpieza";
    }

    // Ruta corregida para actualizar (quitamos el {id} de la URL porque ya viene dentro del objeto limpiezaForm)
    @PostMapping("/limpieza/actualizar")
    public String actualizar(@ModelAttribute Limpieza limpieza) {
        limpiezaService.saveLimpieza(limpieza); // saveLimpieza suele servir para actualizar si el ID ya existe
        return "redirect:/limpieza";
    }
}