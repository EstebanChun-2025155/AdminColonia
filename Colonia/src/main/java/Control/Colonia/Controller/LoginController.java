package Control.Colonia.Controller;

import Control.Colonia.Entity.Residente;
import Control.Colonia.Entity.Seguridad;
import Control.Colonia.Service.ResidenteService;
import Control.Colonia.Service.SeguridadService;
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

    @Autowired
    private SeguridadService seguridadService;

    @GetMapping({"/", "/index"})
    public String login() {
        return "index";
    }

    @PostMapping("/index")
    public String validar(@RequestParam("username") String nombre, @RequestParam("password") String credencial,
            Model model, HttpSession session) {

        Residente res = residenteService.login(nombre, credencial);

        if (res != null && res.getPosicion().equalsIgnoreCase("activo")) {
            session.setAttribute("usuarioLogueado", res);
            session.setAttribute("tipoUsuario", "RESIDENTE");
            session.setAttribute("idResidente", res.getIdResidente());
            return "redirect:/home";
        }

        Seguridad seg = seguridadService.login(nombre, credencial);
        if (seg != null) {
            session.setAttribute("usuarioLogueado", seg);
            session.setAttribute("tipoUsuario", "SEGURIDAD");
            return "redirect:/home";
        }

        model.addAttribute("error", "Nombre o credencial incorrectos");
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }
}