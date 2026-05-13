package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Seguridad;
import com.Administracion.Colonia.Service.SeguridadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class SeguridadController {

    @Autowired
    private SeguridadService seguridadService;

    @GetMapping("/seguridad")
    public String mostrarSeguridad(Model model) {
        List<Seguridad> lista = seguridadService.getAllSeguridad();
        model.addAttribute("seguridad", lista);
        model.addAttribute("seguridadForm", new Seguridad());
        return "seguridad";
    }

    @GetMapping("/eliminar/seguridad/{id}")
    public String eliminar(@PathVariable Integer id) {
        seguridadService.deleteSeguridad(id);
        return "redirect:/seguridad";
    }

    @GetMapping("/nueva/seguridad")
    public String nuevaSeguridad(Model model) {
        model.addAttribute("seguridad", seguridadService.getAllSeguridad());
        model.addAttribute("seguridadForm", new Seguridad());
        model.addAttribute("tab", "registrar");
        return "seguridad";
    }

    @PostMapping("/guardar/seguridad")
    public String guardar(@Valid @ModelAttribute("seguridadForm") Seguridad seguridad, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("seguridad", seguridadService.getAllSeguridad());
            model.addAttribute("tab", "registrar");
            return "seguridad";
        }
        try {
            seguridadService.saveSeguridad(seguridad);
        } catch (RuntimeException e) {
            model.addAttribute("seguridad", seguridadService.getAllSeguridad());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "registrar");
            return "seguridad";
        }
        return "redirect:/seguridad";
    }

    @GetMapping("/editar/seguridad/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Seguridad seguridad = seguridadService.getSeguridadById(id);
        model.addAttribute("seguridad", seguridadService.getAllSeguridad());
        model.addAttribute("seguridadForm", seguridad);
        model.addAttribute("tab", "editar");
        return "seguridad";
    }

    @PostMapping("/actualizar/seguridad/{id}")
    public String actualizar(@PathVariable Integer id, @Valid @ModelAttribute("seguridadForm") Seguridad seguridad, BindingResult result, Model model) {
        if (result.hasErrors()) {
            seguridad.setIdSeguridad(id);
            model.addAttribute("seguridad", seguridadService.getAllSeguridad());
            model.addAttribute("tab", "editar");
            return "seguridad";
        }
        try {
            seguridadService.updateSeguridad(id, seguridad);
        } catch (RuntimeException e) {
            seguridad.setIdSeguridad(id);
            model.addAttribute("seguridad", seguridadService.getAllSeguridad());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "editar");
            return "seguridad";
        }
        return "redirect:/seguridad";
    }

    @GetMapping("/buscar/seguridad")
    public String buscarSeguridad(@RequestParam Integer id, Model model) {
        try {
            Seguridad seguridad = seguridadService.getSeguridadById(id);
            model.addAttribute("seguridad", List.of(seguridad));
        } catch (Exception e) {
            model.addAttribute("seguridad", List.of());
            model.addAttribute("errorGeneral", "No se encontró el empleado con ID: " + id);
        }
        model.addAttribute("seguridadForm", new Seguridad());
        model.addAttribute("tab", "consultar");
        return "seguridad";
    }
}