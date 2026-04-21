package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Multa;
import com.Administracion.Colonia.Service.MultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Multa")
public class MultaController {
    private final MultaService multaService;

    public MultaController(MultaService multaService) {
        this.multaService = multaService;
    }

    @GetMapping
    public List<Multa> getAllMulta(){ return multaService.getAllMulta(); }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMultaById(@PathVariable Integer id){
        try {
            Multa buscarId = multaService.getMultaById(id);
            return new ResponseEntity<>(buscarId,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createMulta(@Valid @RequestBody Multa multa, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }

        try {
            Multa createMulta = multaService.saveMulta(multa);
            return new ResponseEntity<>(createMulta, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMulta(@PathVariable Integer id, @Valid @RequestBody Multa multa, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Multa updateMulta = multaService.updateMulta(id, multa);
            return ResponseEntity.ok(updateMulta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMulta(@PathVariable Integer id){
        try {
            if(multaService.getMultaById(id) == null) {
                return ResponseEntity.status(404).body("No existe esta Multa");
            }
            multaService.deleteMulta(id);
            return ResponseEntity.status(202).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar la Multa");
        }
    }
}