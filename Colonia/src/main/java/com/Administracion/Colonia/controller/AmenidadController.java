package com.Administracion.Colonia.controller;

import com.Administracion.Colonia.entity.Amenidad;
import com.Administracion.Colonia.service.AmenidadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/amenidades") // Ruta solicitada
public class AmenidadController {

    private final AmenidadService amenidadService;

    public AmenidadController(AmenidadService amenidadService){
        this.amenidadService = amenidadService;
    }

    @GetMapping
    public String listarAmenidades(Model model) {
        List<Amenidad> lista = amenidadService.getAllAmenidad();
        model.addAttribute("amenidades", lista);
        return "Amenidades";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("amenidad") Amenidad amenidad, RedirectAttributes flash) {
        try {
            amenidadService.saveAmenidad(amenidad);
            flash.addFlashAttribute("success", "Operación realizada con éxito");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/amenidades";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes flash) {
        try {
            amenidadService.deleteAmenidad(id);
            flash.addFlashAttribute("success", "Amenidad eliminada correctamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se pudo eliminar la amenidad");
        }
        return "redirect:/amenidades";
    }
}