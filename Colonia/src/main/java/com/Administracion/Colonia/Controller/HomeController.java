package com.Administracion.Colonia.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String mostrarHome () {
        return "VistaHome";
    }

    @GetMapping("/limpieza")
    public String mostrarLimpieza () {
        return "VistaLimpieza";
    }

    @GetMapping("/seguridad")
    public String mostrarSeguridad () {
        return "VistaSeguridad";
    }

    }