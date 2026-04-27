package com.Administracion.Colonia.controller;

import com.Administracion.Colonia.entity.Vehiculo;
import com.Administracion.Colonia.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    // MOSTRAR LISTA
    @GetMapping
    public String mostrarVehiculos(Model model) {
        List<Vehiculo> lista = vehiculoService.getAllVehiculo();
        model.addAttribute("vehiculos", lista);
        model.addAttribute("vehiculoForm", new Vehiculo());
        return "Vehiculos";
    }

    // NUEVO
    @GetMapping("/nuevo")
    public String nuevoVehiculo(Model model) {
        model.addAttribute("vehiculos", vehiculoService.getAllVehiculo());
        model.addAttribute("vehiculoForm", new Vehiculo());
        return "Vehiculos";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("vehiculoForm") Vehiculo vehiculo,
                          BindingResult br,
                          Model model) {

        if (br.hasErrors()) {
            model.addAttribute("vehiculos", vehiculoService.getAllVehiculo());
            return "Vehiculos";
        }

        vehiculoService.saveVehiculo(vehiculo);
        return "redirect:/vehiculos";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Vehiculo vehiculo = vehiculoService.getVehiculoById(id);

        model.addAttribute("vehiculos", vehiculoService.getAllVehiculo());
        model.addAttribute("vehiculoForm", vehiculo);
        model.addAttribute("tab", "editar");

        return "Vehiculos";
    }

    // ACTUALIZAR
    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id,
                             @Valid @ModelAttribute("vehiculoForm") Vehiculo vehiculo,
                             BindingResult br,
                             Model model) {

        if (br.hasErrors()) {
            model.addAttribute("vehiculos", vehiculoService.getAllVehiculo());
            return "Vehiculos";
        }

        vehiculoService.updateVehiculo(id, vehiculo);
        return "redirect:/vehiculos";
    }

    // ELIMINAR
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        vehiculoService.deleteVehiculo(id);
        return "redirect:/vehiculos";
    }

    // BUSCAR
    @GetMapping("/buscar")
    public String buscar(@RequestParam Integer id, Model model) {
        Vehiculo vehiculo = vehiculoService.getVehiculoById(id);

        model.addAttribute("vehiculos", List.of(vehiculo));
        model.addAttribute("vehiculoForm", new Vehiculo());

        return "Vehiculos";
    }
}