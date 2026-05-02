package Control.Colonia.Controller;

import Control.Colonia.Entity.Pago;
import Control.Colonia.Service.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping("/pago")
    public String mostrarPago (Model model) {
        model.addAttribute("pagos", pagoService.getAllPago());
        model.addAttribute("pagoNuevo", new Pago());
        model.addAttribute("panelActivo", "consultar");
        return "VistaPago";
    }

    @GetMapping("/pago/nueva")
    public String nuevoPago(Model model) {
        model.addAttribute("pagos", pagoService.getAllPago());
        model.addAttribute("pagoNuevo", new Pago());
        model.addAttribute("panelActivo", "registrar");
        return "VistaPago";
    }

    @PostMapping("/pago/nueva")
    public String guardarPago(@Valid @ModelAttribute("pagoNuevo") Pago pago,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pagos", pagoService.getAllPago());
            model.addAttribute("panelActivo", "registrar");
            return "VistaPago";
        }
        try {
            pagoService.savePago(pago);
        } catch (RuntimeException e) {
            model.addAttribute("pagos", pagoService.getAllPago());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("panelActivo", "registrar");
            return "VistaPago";
        }
        return "redirect:/pago";
    }

    @GetMapping("/pago/editar/{id}")
    public String editarPago(@PathVariable Integer id, Model model) {
        model.addAttribute("pagos", pagoService.getAllPago());
        model.addAttribute("pagoNuevo", new Pago());
        model.addAttribute("pagoEditar", pagoService.getPagoById(id));
        model.addAttribute("panelActivo", "editar");
        return "VistaPago";
    }

    @PostMapping("/pago/editar/{id}")
    public String actualizarPago(@PathVariable Integer id,
                                 @Valid @ModelAttribute("pagoEditar") Pago pago,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            pago.setIdPago(id);
            model.addAttribute("pagos", pagoService.getAllPago());
            model.addAttribute("pagoNuevo", new Pago());
            model.addAttribute("panelActivo", "editar");
            return "VistaPago";
        }
        try {
            pagoService.updatePago(id, pago);
        } catch (RuntimeException e) {
            pago.setIdPago(id);
            model.addAttribute("pagos", pagoService.getAllPago());
            model.addAttribute("pagoNuevo", new Pago());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("panelActivo", "editar");
            return "VistaPago";
        }
        return "redirect:/pago";
    }

    @GetMapping("/pago/eliminar/{id}")
    public String eliminarPago(@PathVariable Integer id) {
        pagoService.deletePago(id);
        return "redirect:/pago";
    }

    @GetMapping("/pago/buscar")
    public String buscarPago(@RequestParam Integer id, Model model) {
        Pago pago = pagoService.getPagoById(id);
        model.addAttribute("pagos", List.of(pago));
        model.addAttribute("pagoNuevo", new Pago());
        model.addAttribute("panelActivo", "consultar");
        return "VistaPago";
    }
}