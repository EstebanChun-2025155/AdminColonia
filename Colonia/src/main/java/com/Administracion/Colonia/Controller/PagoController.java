package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Pago;
import com.Administracion.Colonia.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String guardarPago(@ModelAttribute Pago pago) {
        pagoService.savePago(pago);
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
    public String actualizarPago(@PathVariable Integer id, @ModelAttribute Pago pago) {
        pagoService.updatePago(id, pago);
        return "redirect:/pago";
    }

    @GetMapping("/pago/eliminar/{id}")
    public String eliminarPago(@PathVariable Integer id) {
        pagoService.deletePago(id);
        return "redirect:/pago";
    }
}