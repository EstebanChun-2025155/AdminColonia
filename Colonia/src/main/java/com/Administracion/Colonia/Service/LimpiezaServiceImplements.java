package com.Administracion.Colonia.Service;

import com.Administracion.Colonia.Entity.Limpieza;
import com.Administracion.Colonia.Repository.LimpiezaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LimpiezaServiceImplements implements LimpiezaService{
    private final LimpiezaRepository limpiezaRepository;

    public  LimpiezaServiceImplements(LimpiezaRepository limpiezaRepository) {
        this.limpiezaRepository = limpiezaRepository;
    }
    @Override
    public List<Limpieza> getAllLimpieza() { return limpiezaRepository.findAll(); }

    @Override
    public Limpieza getLimpiezaById(Integer id) { return limpiezaRepository.getReferenceById(id); }

    @Override
    public Limpieza saveLimpieza(Limpieza limpieza) throws  RuntimeException {
        try {
            if (limpiezaRepository.existsByNombreAndPuestoAndJornadaAndSalarioAndTelefono(
                    limpieza.getNombre(),
                    limpieza.getPuesto(),
                    limpieza.getJornada(),
                    limpieza.getSalario(),
                    limpieza.getTelefono())) {
                throw new RuntimeException("Ya existe un empleado de limpieza con estos datos");
            }

            return limpiezaRepository.save(limpieza);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Limpieza updateLimpieza(Integer id, Limpieza limpieza) {
        Limpieza existingLimpieza = limpiezaRepository.findById(id).orElseThrow(() -> new RuntimeException("El empleado de limpieza no existe"));

        if (limpiezaRepository.existsByNombreAndPuestoAndJornadaAndSalarioAndTelefono(
                limpieza.getNombre(),
                limpieza.getPuesto(),
                limpieza.getJornada(),
                limpieza.getSalario(),
                limpieza.getTelefono())){
            throw new RuntimeException("Ya existe un empleado de limpieza con estos datos");
        }

        existingLimpieza.setNombre(limpieza.getNombre());
        existingLimpieza.setPuesto(limpieza.getPuesto());
        existingLimpieza.setJornada(limpieza.getJornada());
        existingLimpieza.setSalario(limpieza.getSalario());
        existingLimpieza.setTelefono(limpieza.getTelefono());

        return limpiezaRepository.save(existingLimpieza);
    }

    @Override
    public void deleteLimpieza(Integer id) {
        if (!limpiezaRepository.existsById(id)){
            throw new RuntimeException("Este id no existe");
        }
        limpiezaRepository.deleteById(id);
    }
}