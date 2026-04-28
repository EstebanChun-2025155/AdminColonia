package com.Administracion.Colonia.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpSession session){
        if(session.getAttribute("usuarioLogueado") == null){
            return "redirect:/login";
        }
        return "VistaHome";
    }

}