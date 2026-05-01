package com.Administracion.Colonia.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String mostrarHome(HttpSession session, Model model) {

        String tipo = (String) session.getAttribute("tipoUsuario");

        if (tipo == null) {
            return "redirect:/login";
        }

        model.addAttribute("isResidente", tipo.equals("RESIDENTE"));
        model.addAttribute("isSeguridad", tipo.equals("SEGURIDAD"));

        return "VistaHome";
    }
}