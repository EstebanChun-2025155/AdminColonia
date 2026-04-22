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
    public String mostrarSeguridad(Model model){
        List<Seguridad> lista = seguridadService.getAllSeguridad();
        model.addAttribute("listaSeguridad", lista);

        return "VistaSeguridad";
    }
}