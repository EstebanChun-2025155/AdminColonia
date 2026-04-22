package com.Administracion.Colonia.controller;

import com.Administracion.Colonia.entity.Amenidad;
import com.Administracion.Colonia.service.AmenidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/amenidades")
public class AmenidadController {

    @Autowired
    private AmenidadService amenidadService;

    // RUTA PRINCIPAL
    @GetMapping
    public String inicio(Model model) {
        model.addAttribute("amenidades", amenidadService.getAllAmenidad());
        // Si no viene de un redirect con errores, mandamos un objeto limpio
        if (!model.containsAttribute("amenidad")) {
            model.addAttribute("amenidad", new Amenidad());
        }
        return "Amenidades";
    }

    // RUTA DE EDICIÓN (La que causaba el error)
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Amenidad a = (Amenidad) amenidadService.getAmenidadById(id);
        if (a == null) {
            return "redirect:/amenidades";
        }
        model.addAttribute("amenidad", a); // Cargamos el objeto encontrado
        model.addAttribute("amenidades", amenidadService.getAllAmenidad()); // Cargamos la tabla
        return "Amenidades";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("amenidad") Amenidad amenidad, RedirectAttributes flash) {
        try {
            amenidadService.saveAmenidad(amenidad);
            flash.addFlashAttribute("success", "Registro procesado con éxito.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/amenidades";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes flash) {
        try {
            amenidadService.deleteAmenidad(id);
            flash.addFlashAttribute("success", "Eliminado correctamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar: " + e.getMessage());
        }
        return "redirect:/amenidades";
    }
}