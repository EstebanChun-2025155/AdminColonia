package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Accesos;
import com.Administracion.Colonia.Service.AccesosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accesos")
public class AccesosController {
    private final AccesosService accesosService;

    public AccesosController(AccesosService accesosService) {
        this.accesosService = accesosService;
    }

    @GetMapping
    public List<Accesos> getAccesos(){
        return accesosService.getAllAccesos();
    }

    @PostMapping
    public ResponseEntity<Object> createAcceso(@Valid @RequestBody Accesos accesos, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Accesos createAcceso = accesosService.saveAcceso(accesos);
            return new ResponseEntity<>(createAcceso, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAcceso(@Valid @RequestBody Accesos accesos, @PathVariable Integer id, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Accesos updateAcceso = accesosService.updateAcceso(id, accesos);
            return new ResponseEntity<>(updateAcceso, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAccesosById(@PathVariable Integer id){
        try {
            Accesos searchedAccesos = accesosService.getAccesosById(id);
            return new ResponseEntity<>(searchedAccesos,HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el acceso");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAccesosById(@PathVariable Integer id){
        try {
            if(accesosService.getAccesosById(id) == null) {
                return ResponseEntity.status(404).body("No exsite esta Acceso");
            }
            accesosService.deleteAccesos(id);
            return  ResponseEntity.status(202).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar Acceso");
        }
    }
}