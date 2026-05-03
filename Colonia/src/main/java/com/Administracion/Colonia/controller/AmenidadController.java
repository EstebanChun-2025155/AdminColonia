package com.Administracion.Colonia.controller;

import com.Administracion.Colonia.entity.Amenidad;
import com.Administracion.Colonia.service.AmenidadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        model.addAttribute("amenidades", amenidadService.getAllAmenidad());
        model.addAttribute("amenidadForm", new Amenidad());
        model.addAttribute("tab", "registrar");
        return "Amenidades";
    }

    // GUARDAR (CORREGIDO)
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("amenidadForm") Amenidad amenidad,
                          BindingResult result,
                          Model model) {


        if (result.hasErrors()) {
            model.addAttribute("amenidades", amenidadService.getAllAmenidad());
            model.addAttribute("tab", "registrar");
            return "Amenidades"; // Retorna la vista, NO un redirect
        }

        try {
            amenidadService.saveAmenidad(amenidad);
        } catch (RuntimeException e) {
            model.addAttribute("amenidades", amenidadService.getAllAmenidad());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "registrar");
            return "Amenidades";
        }

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
    public String actualizar(@PathVariable Integer id,
                             @Valid @ModelAttribute("amenidadForm") Amenidad amenidad,
                             BindingResult result,
                             Model model) {

        // 1. Verificar si hay errores de validación
        if (result.hasErrors()) {
            amenidad.setIdAmenidad(id); // Asegúrate que el objeto mantenga su ID
            model.addAttribute("amenidades", amenidadService.getAllAmenidad());
            model.addAttribute("tab", "editar");
            return "Amenidades";
        }

        try {
            amenidadService.updateAmenidad(id, amenidad);
        } catch (RuntimeException e) {
            amenidad.setIdAmenidad(id);
            model.addAttribute("amenidades", amenidadService.getAllAmenidad());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "editar");
            return "Amenidades";
        }

        return "redirect:/amenidades";
    }

    // ELIMINAR
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        amenidadService.deleteAmenidad(id);
        return "redirect:/amenidades";
    }

    //BUSCAR
    @GetMapping("/buscar")
    public String buscarAmenidad(@RequestParam(name = "id", required = false) Integer id, Model model) {
        if (id == null) {
            return "redirect:/amenidades";
        }
        Optional<Amenidad> amenidadOpt = amenidadService.buscarPorId(id);

        if (amenidadOpt.isPresent()) {
            // Convertimos el objeto único en una lista para que la tabla lo muestre bien
            model.addAttribute("amenidades", List.of(amenidadOpt.get()));
            model.addAttribute("errorBusqueda", null);
        } else {
            // Si no existe, mandamos lista vacía y activamos el mensaje de error
            model.addAttribute("amenidades", List.of());
            model.addAttribute("errorBusqueda", "El ID " + id + " no existe en nuestros registros.");
        }

        model.addAttribute("tab", "consultar");
        model.addAttribute("amenidadForm", new Amenidad());
        return "Amenidades";
    }
}