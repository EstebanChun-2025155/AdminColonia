package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Seguridad;
import com.Administracion.Colonia.Service.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class SeguridadController {

    @Autowired
    private SeguridadService seguridadService;

    @GetMapping("/seguridad")
    public String mostrarSeguridad(Model model){
        List<Seguridad> lista = seguridadService.getAllSeguridad();
        model.addAttribute("seguridad", lista);
        model.addAttribute("seguridadForm", new Seguridad());
        return "seguridad";
    }

    @PostMapping("/eliminar/seguridad/{id}")
    public String eliminar(@PathVariable Integer id){
        seguridadService.deleteSeguridad(id);
        return "redirect:/seguridad";
    }

    @GetMapping("/nueva/seguridad")
    public String nuevaSeguridad(Model model){
        model.addAttribute("seguridadForm", new Seguridad());
        return "seguridad";
    }

    @PostMapping("/guardar/seguridad")
    public String guardar(@ModelAttribute Seguridad seguridad){
        seguridadService.saveSeguridad(seguridad);
        return "redirect:/seguridad";
    }

    @GetMapping("/editar/seguridad/{id}")
    public String editar(@PathVariable Integer id, Model model){
        Seguridad seguridad = seguridadService.getSeguridadById(id);
        model.addAttribute("seguridad", seguridadService.getAllSeguridad());
        model.addAttribute("seguridadForm", seguridad);
        model.addAttribute("tab", "editar");
        return "seguridad";
    }

    @PostMapping("/actualizar/seguridad/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute Seguridad seguridad){
        seguridadService.updateSeguridad(id, seguridad);
        return "redirect:/seguridad";

    }

    @GetMapping("/buscar/seguridad")
    public String buscarSeguridad(@RequestParam Integer id, Model model){
        Seguridad seguridad = seguridadService.getSeguridadById(id);

        model.addAttribute("seguridad", List.of(seguridad));
        model.addAttribute("seguridadForm", new Seguridad());

        return "seguridad";
    }
}