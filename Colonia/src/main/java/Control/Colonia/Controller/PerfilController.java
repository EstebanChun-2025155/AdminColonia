package Control.Colonia.Controller;

import Control.Colonia.Entity.Residente;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilController {

    @GetMapping("/perfil")
    public String mostrarPerfil(HttpSession session, Model model) {

        Residente residente = (Residente) session.getAttribute("usuarioLogueado");

        if (residente == null) {
            return "redirect:/login";
        }

        model.addAttribute("residente", residente);

        return "perfil";
    }
}