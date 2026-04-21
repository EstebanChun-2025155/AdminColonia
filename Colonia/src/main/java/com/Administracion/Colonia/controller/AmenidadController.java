package com.Administracion.Colonia.controller;

import com.Administracion.Colonia.entity.Amenidad;
import com.Administracion.Colonia.service.AmenidadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/amenidades")
public class AmenidadController {

    private final AmenidadService amenidadService;

    public AmenidadController(AmenidadService amenidadService){
        this.amenidadService = amenidadService;
    }


    @GetMapping
    public List<Amenidad> listarTodos(){return amenidadService.getAllAmenidad();}


    @PostMapping
   public ResponseEntity<Object> createAmenidad(@Valid @RequestBody Amenidad amenidad, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Amenidad createAmenidad = amenidadService.saveAmenidad(amenidad);
            return new ResponseEntity<>(createAmenidad, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAmenidadById(@PathVariable Integer id){
        try {
            Amenidad searchedAmenidad = amenidadService.getAmenidadByid(id);
            return new ResponseEntity<>(searchedAmenidad,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAmenidad(@PathVariable Integer id){
        try {
            if (amenidadService.getAmenidadByid(id) == null) {
                return ResponseEntity.status(404).body("No Existe esta Amenidad");
            }
            amenidadService.deleteAmenidad(id);
            return ResponseEntity.status(202).build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar Amenidad");
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAmenidad(@PathVariable Integer id , @Valid @RequestBody Amenidad amenidad, BindingResult br){
    if (br.hasErrors()){
        return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
    }
    try {
        Amenidad actualizado = amenidadService.updateAmenidad(id,amenidad);
            return ResponseEntity.ok(actualizado);
     }  catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
         }
     }
}

