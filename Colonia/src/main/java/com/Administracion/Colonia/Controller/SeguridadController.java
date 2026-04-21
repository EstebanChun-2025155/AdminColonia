package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Seguridad;
import com.Administracion.Colonia.Service.SeguridadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Seguridad")
public class SeguridadController {
    private final SeguridadService seguridadService;

    public SeguridadController(SeguridadService seguridadService){
        this.seguridadService = seguridadService; }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSeguridadById(@PathVariable Integer id){
        try {
            Seguridad buscarId = seguridadService.getSeguridadById(id);
            return new ResponseEntity<>(buscarId,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createSeguridad(@Valid @RequestBody Seguridad seguridad, BindingResult br){
        if  (br.hasErrors()){
            return  ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }
        try{
            Seguridad createSeguridad = seguridadService.saveSeguridad(seguridad);
            return new ResponseEntity<>(createSeguridad, HttpStatus.CREATED);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSeguridad(@PathVariable Integer id) {
        try {
            if (seguridadService.getSeguridadById(id) == null ) {
                return ResponseEntity.status(404).body("No existe el empleado de seguridad");
            }
            seguridadService.deleteSeguridad(id);
            return ResponseEntity.status(202).build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar al empleado de seguridad");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSeguridad( @PathVariable Integer id, @RequestBody @Valid Seguridad seguridad, BindingResult br) {
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Seguridad actualizado = seguridadService.updateSeguridad(id, seguridad);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}