package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Seguridad;
import com.Administracion.Colonia.Service.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SeguridadController {

    @Autowired
    private SeguridadService seguridadService;

    @GetMapping("/seguridad")
    public String mostrarSeguridad(Model model) {
        model.addAttribute("listaSeguridad", seguridadService.getAllSeguridad());
        model.addAttribute("seguridadForm", new Seguridad());
        return "VistaSeguridad";
    }

    @PostMapping("/seguridad/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        seguridadService.deleteSeguridad(id);
        return "redirect:/seguridad";
    }

    @PostMapping("/seguridad/guardar")
    public String guardar(@ModelAttribute Seguridad seguridad) {
        seguridadService.saveSeguridad(seguridad);
        return "redirect:/seguridad";
    }

    @GetMapping("/seguridad/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Seguridad seguridad = seguridadService.getSeguridadById(id);
        model.addAttribute("listaSeguridad", seguridadService.getAllSeguridad());
        model.addAttribute("seguridadForm", seguridad);
        model.addAttribute("tab", "editar");
        return "VistaSeguridad";
    }

    @PostMapping("/seguridad/actualizar")
    public String actualizar(@ModelAttribute Seguridad seguridad) {
        seguridadService.saveSeguridad(seguridad);
        return "redirect:/seguridad";
    }
}