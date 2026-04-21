package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Limpieza;
import com.Administracion.Colonia.Entity.Seguridad;
import com.Administracion.Colonia.Service.LimpiezaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Limpieza")
public class LimpiezaController {
    private final LimpiezaService limpiezaService;

    public LimpiezaController(LimpiezaService limpiezaService){
        this.limpiezaService = limpiezaService; }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLimpiezaById(@PathVariable Integer id){
        try {
            Limpieza buscarId = limpiezaService.getLimpiezaById(id);
            return new ResponseEntity<>(buscarId,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createLimpieza(@Valid @RequestBody Limpieza limpieza, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Limpieza createLimpieza = limpiezaService.saveLimpieza(limpieza);
            return new ResponseEntity<>(createLimpieza, HttpStatus.CREATED);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLimpieza(@PathVariable Integer id) {
        try {
            if (limpiezaService.getLimpiezaById(id) == null) {
                return ResponseEntity.status(404).body("No existe el empleado de limpieza");
            }
            limpiezaService.deleteLimpieza(id);
            return ResponseEntity.status(202).build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar al empleado de limpieza");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLimpieza( @PathVariable Integer id, @RequestBody @Valid Limpieza limpieza, BindingResult br) {
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Limpieza actualizado = limpiezaService.updateLimpieza(id, limpieza);
            return ResponseEntity.ok(actualizado);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}