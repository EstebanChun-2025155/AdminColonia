package com.Administracion.Colonia.controller;

import com.Administracion.Colonia.entity.Vehiculo;
import com.Administracion.Colonia.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller; // Cambiado de @RestController
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@Controller // Cambiado para poder retornar vistas HTML
@RequestMapping("/vehiculos") // Ruta para la vista
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService){
        this.vehiculoService = vehiculoService;
    }

    /**
     * Este método carga la página HTML en el navegador.
     * Acceso: GET /vehiculos
     */
    @GetMapping
    public String listarVehiculosVista(Model model) {
        List<Vehiculo> vehiculos = vehiculoService.getAllVehiculo();
        model.addAttribute("listaVehiculos", vehiculos);
        model.addAttribute("nuevoVehiculo", new Vehiculo()); // Para el formulario de creación
        return "Vehiculos"; // Retorna Vehiculos.html de la carpeta templates
    }

    /**
     * Mantenemos tus métodos de API, pero usamos @ResponseBody
     * para que sigan devolviendo JSON en lugar de buscar un HTML.
     */
    @GetMapping("/api/listar")
    @ResponseBody
    public List<Vehiculo> listarTodosApi(){
        return vehiculoService.getAllVehiculo();
    }

    @PostMapping("/guardar")
    public String saveVehiculoThymeleaf(@Valid @ModelAttribute("nuevoVehiculo") Vehiculo vehiculo,
                                        BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("listaVehiculos", vehiculoService.getAllVehiculo());
            return "Vehiculos"; // Regresa a la vista con errores
        }
        vehiculoService.saveVehiculo(vehiculo);
        return "redirect:/vehiculos"; // Recarga la página para ver el cambio
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteVehiculo(@PathVariable Integer id){
        try {
            vehiculoService.deleteVehiculo(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar");
        }
    }
}