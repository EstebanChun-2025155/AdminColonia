package Control.Colonia.Controller;

import Control.Colonia.Entity.Vehiculo;
import Control.Colonia.Repository.CasaRepository;
import Control.Colonia.Service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private CasaRepository casaRepository;

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
        model.addAttribute("tab", "registrar");

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

        if (!casaRepository.existsById(vehiculo.getIdCasa())){
            br.rejectValue("idCasa", "error.casa", "El ID de la vivienda no Existe");

            model.addAttribute("vehiculos", vehiculoService.getAllVehiculo());
            model.addAttribute("tab", "registrar");
            return "Vehiculos";
        }

        try {
            vehiculoService.saveVehiculo(vehiculo);
        } catch (Exception e) {
            model.addAttribute("vehiculos", vehiculoService .getAllVehiculo());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "registrar");
            return "Vehiculos";
        }

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
            vehiculo.setIdVehiculo(id);
            model.addAttribute("vehiculos", vehiculoService.getAllVehiculo());
            model.addAttribute("tab", "editar");
            return "Vehiculos";
        }

        if (!casaRepository.existsById(vehiculo.getIdCasa())){
            br.rejectValue("idCasa", "error.casa", "El ID de la vivienda no Existe");

            vehiculo.setIdVehiculo(id);
            model.addAttribute("vehiculo", vehiculoService.getAllVehiculo());
            model.addAttribute("vehiculo", new Vehiculo());
            model.addAttribute("tab", "registrar");
            return "Vehiculos";
        }

        try {
            vehiculoService.updateVehiculo(id, vehiculo);
        } catch (RuntimeException e) {
            vehiculo.setIdVehiculo(id);
            model.addAttribute("vehiculos", vehiculoService.getAllVehiculo());
            model.addAttribute("errorGeneral", e.getMessage());
            model.addAttribute("tab", "editar");
            return "Vehiculos";
        }

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
       try {
           Vehiculo vehiculo = vehiculoService.getVehiculoById(id);
           model.addAttribute("vehiculos", List.of(vehiculo));
       } catch (Exception e) {
           model.addAttribute("vehiculos", List.of());
           model.addAttribute("errorGeneral", "No existe un vehiculo con ID:" + id);
       }
       model.addAttribute("vehiculoForm", new Vehiculo());
       model.addAttribute("tab", "consultar");
        return "Vehiculos";
    }
}