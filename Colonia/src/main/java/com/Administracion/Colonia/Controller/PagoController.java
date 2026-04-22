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
        List<Pago> pagos = pagoService.getAllPago();
        model.addAttribute("pagos", pagos);
        return "VistaPago";
    }
}