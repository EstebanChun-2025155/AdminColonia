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

    @GetMapping("/multa")
    public String mostrarMulta () {
        return "VistaMulta";
    }

    @GetMapping("/pago")
    public String mostrarPago () {
        return "VistaPago";
    }

}