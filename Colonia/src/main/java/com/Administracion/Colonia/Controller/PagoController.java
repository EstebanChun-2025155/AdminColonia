package com.Administracion.Colonia.Controller;

import com.Administracion.Colonia.Entity.Multa;
import com.Administracion.Colonia.Entity.Pago;
import com.Administracion.Colonia.Service.PagoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Pago")
public class PagoController {
    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public List<Pago> getAllPago(){ return pagoService.getAllPago(); }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPagoById(@PathVariable Integer id){
        try {
            Pago buscarId = pagoService.getPagoById(id);
            return new ResponseEntity<>(buscarId,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> createPago(@Valid @RequestBody Pago pago, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }

        try {
            Pago createPago = pagoService.savePago(pago);
            return new ResponseEntity<>(createPago, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePago(@PathVariable Integer id, @Valid @RequestBody Pago pago, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Pago updatePago = pagoService.updatePago(id, pago);
            return ResponseEntity.ok(updatePago);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePago(@PathVariable Integer id){
        try {
            if(pagoService.getPagoById(id) == null) {
                return ResponseEntity.status(404).body("No existe este Pago");
            }
            pagoService.deletePago(id);
            return ResponseEntity.status(202).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el Pago");
        }
    }
}