package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Residente;
import com.Administracion.Colonia.Entity.Seguridad;
import com.Administracion.Colonia.Service.ResidenteService;
import com.Administracion.Colonia.Service.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private ResidenteService residenteService;

    @Autowired
    private SeguridadService seguridadService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String validar(
            @RequestParam("username") String nombre,
            @RequestParam("password") String credencial,
            Model model,
            HttpSession session) {

        Residente res = residenteService.login(nombre, credencial);
        if (res != null && res.getPosicion().equalsIgnoreCase("activo")) {
            session.setAttribute("usuarioLogueado", res);
            session.setAttribute("tipoUsuario", "RESIDENTE");
            return "redirect:/home";
        }

        Seguridad seg = seguridadService.login(nombre, credencial);
        if (seg != null) {
            session.setAttribute("usuarioLogueado", seg);
            session.setAttribute("tipoUsuario", "SEGURIDAD");
            return "redirect:/home";
        }

        model.addAttribute("error", "Nombre o credencial incorrectos");
        return "login";
    }
}