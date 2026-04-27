package com.Administracion.Colonia.controller;

import com.Administracion.Colonia.entity.Amenidad;
import com.Administracion.Colonia.service.AmenidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/amenidades")
public class AmenidadController {

    @Autowired
    private AmenidadService amenidadService;

    // MOSTRAR LISTA
    @GetMapping
    public String mostrarAmenidades(Model model) {
        List<Amenidad> lista = amenidadService.getAllAmenidad();
        model.addAttribute("amenidades", lista);
        model.addAttribute("amenidadForm", new Amenidad());
        return "Amenidades";
    }

    // NUEVO REGISTRO
    @GetMapping("/nueva")
    public String nuevaAmenidad(Model model) {
        model.addAttribute("amenidadForm", new Amenidad());
        model.addAttribute("amenidades", amenidadService.getAllAmenidad());
        return "Amenidades";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Amenidad amenidad) {
        amenidadService.saveAmenidad(amenidad);
        return "redirect:/amenidades";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Amenidad amenidad = amenidadService.getAmenidadById(id);

        model.addAttribute("amenidades", amenidadService.getAllAmenidad());
        model.addAttribute("amenidadForm", amenidad);
        model.addAttribute("tab", "editar");

        return "Amenidades";
    }

    // ACTUALIZAR
    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute Amenidad amenidad) {
        amenidadService.updateAmenidad(id, amenidad);
        return "redirect:/amenidades";
    }

    // ELIMINAR
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        amenidadService.deleteAmenidad(id);
        return "redirect:/amenidades";
    }

    // BUSCAR
    @GetMapping("/buscar")
    public String buscar(@RequestParam Integer id, Model model) {
        Amenidad amenidad = amenidadService.getAmenidadById(id);

        model.addAttribute("amenidades", List.of(amenidad));
        model.addAttribute("amenidadForm", new Amenidad());

        return "Amenidades";
    }
}