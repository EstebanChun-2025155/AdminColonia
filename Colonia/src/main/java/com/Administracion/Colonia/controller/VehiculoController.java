package com.Administracion.Colonia.controller;
import com.Administracion.Colonia.entity.Vehiculo;
import com.Administracion.Colonia.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService){
        this.vehiculoService = vehiculoService;
    }


    @GetMapping
    public List<Vehiculo> listarTodos(){
        return vehiculoService.getAllVehiculo();
    }


    @PostMapping
    public ResponseEntity<Object> createVehiculo(@Valid @RequestBody Vehiculo vehiculo, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }

        try {
            Vehiculo crateVehiculo = vehiculoService.saveVehiculo(vehiculo);
            return new ResponseEntity<>(crateVehiculo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getVehiculoById(@PathVariable Integer id){
        try {
            Vehiculo searchedVehiculo = vehiculoService.getVehiculoByid(id);
            return new ResponseEntity<>(searchedVehiculo,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVehiculo(@PathVariable Integer id){
        try {
            if(vehiculoService.getVehiculoByid(id) == null) {
                return ResponseEntity.status(404).body("No existe este Vehiculo");
            }
            vehiculoService.deleteVehiculo(id);
            return ResponseEntity.status(202).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar Vehiculo");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVehiculo(@PathVariable Integer id, @Valid @RequestBody Vehiculo vehiculo, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Vehiculo actualizado = vehiculoService.updateVehiculo(id, vehiculo);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }


}
