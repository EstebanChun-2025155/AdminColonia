package com.Administracion.Colonia.controller;

import com.Administracion.Colonia.entity.Vehiculo;
import com.Administracion.Colonia.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        model.addAttribute("vehiculos", vehiculoService.getAllVehiculo());
        model.addAttribute("vehiculoForm", new Vehiculo());
        model.addAttribute("tab", "consultar");
        return "Vehiculos";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("vehiculoForm") Vehiculo vehiculo,
                          BindingResult br,
                          Model model) {

        if (br.hasErrors()) {
            model.addAttribute("vehiculos", vehiculoService.getAllVehiculo());
            model.addAttribute("tab", "registrar");
            return "Vehiculos";
        }

        if (vehiculoService.existePlaca(vehiculo.getPlaca())) {
            model.addAttribute("error", "La placa " + vehiculo.getPlaca() + " ya existe.");
            model.addAttribute("vehiculos", vehiculoService.getAllVehiculo());
            model.addAttribute("tab", "registrar");
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
            model.addAttribute("error", "Error al actualizar los datos.");
            model.addAttribute("tab", "editar");
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

    @GetMapping("/buscar")
    public String buscar(@RequestParam(name = "id", required = false) Integer id, Model model) {
        if (id == null) {
            return "redirect:/vehiculos";
        }

        Optional<Vehiculo> vOpt = vehiculoService.buscarPorId(id);

        if (vOpt.isPresent()) {
            model.addAttribute("vehiculos", List.of(vOpt.get()));
            model.addAttribute("errorBusqueda", null);
        } else {
            // IMPORTANTE: Lista vacía para que la tabla diga "No se encontraron registros"
            model.addAttribute("vehiculos", List.of());
            // Mensaje exacto para activar el bloque rosado
            model.addAttribute("errorBusqueda", "El vehículo con ID " + id + " no existe en nuestros registros.");
        }

        model.addAttribute("vehiculoForm", new Vehiculo());
        model.addAttribute("tab", "consultar");
        return "Vehiculos";
    }
}