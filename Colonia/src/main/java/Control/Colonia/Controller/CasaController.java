package Control.Colonia.Controller;

import Control.Colonia.Entity.Casa;
import Control.Colonia.Service.CasaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Casa")
public class CasaController {
    private final CasaService casaService;

    public CasaController(CasaService casaService) {
        this.casaService = casaService;
    }

    @GetMapping
    public List<Casa> getAlllCasa(){ return casaService.getAllCasa(); }

    @PostMapping
    public ResponseEntity<Object> createCasa(@Valid @RequestBody Casa casa, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }

        try {
            Casa createCasa = casaService.saveCasa(casa);
            return new ResponseEntity<>(createCasa, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCasaById(@PathVariable Integer id){
        try {
            Casa buscarId = casaService.getCasaById(id);
            return new ResponseEntity<>(buscarId,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCasa(@PathVariable Integer id){
        try {
            if(casaService.getCasaById(id) == null) {
                return ResponseEntity.status(404).body("No exsite esta Casa");
            }
            casaService.deleteCasa(id);
            return ResponseEntity.status(202).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar Casa");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCasa(@PathVariable Integer id, @Valid @RequestBody Casa casa, BindingResult br){
        if (br.hasErrors()){
            return ResponseEntity.badRequest().body(br.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Casa actualizado = casaService.updateCasa(id, casa);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}