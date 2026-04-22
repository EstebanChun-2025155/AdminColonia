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
        model.addAttribute("listaLimpieza", lista);

        return "VistaLimpieza";
    }

    @GetMapping("/limpieza/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){

        limpiezaService.deleteLimpieza(id);

        return "redirect:/limpieza";
    }
}