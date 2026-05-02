package Control.Colonia.Controller;

import Control.Colonia.Entity.Residente;
import Control.Colonia.Service.ResidenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private ResidenteService residenteService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String validar(@RequestParam String nombreResidente, @RequestParam String dpiResidente, Model model, HttpSession session) {
        Residente u = residenteService.login(nombreResidente, dpiResidente);
        if (u != null && u.getPosicion().equalsIgnoreCase("activo")) {
            session.setAttribute("usuarioLogueado", u);

            return "redirect:/home";

        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
}
