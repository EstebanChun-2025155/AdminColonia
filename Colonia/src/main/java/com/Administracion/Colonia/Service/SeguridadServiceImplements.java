package com.Administracion.Colonia.Service;

import com.Administracion.Colonia.Entity.Seguridad;
import com.Administracion.Colonia.Repository.SeguridadRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class  SeguridadServiceImplements implements SeguridadService {
    private final SeguridadRepository seguridadRepository;

    public SeguridadServiceImplements(SeguridadRepository seguridadRepository) {
        this.seguridadRepository = seguridadRepository;
    }

    @Override
    public List<Seguridad> getAllSeguridad() {
        return seguridadRepository.findAll();
    }

    @Override
    public Seguridad getSeguridadById(Integer id) {  return seguridadRepository.findById(id).orElseThrow(() -> new RuntimeException("Este empleado de seguridad no existe"));
    }

    @Override
    public Seguridad saveSeguridad(Seguridad seguridad) throws RuntimeException {
        try {
            if (seguridadRepository.existsByNombreAndPuestoAndJornadaAndSalarioAndTelefonoSeguridadAndDpiSeguridad(
                    seguridad.getNombre(),
                    seguridad.getPuesto(),
                    seguridad.getJornada(),
                    seguridad.getSalario(),
                    seguridad.getTelefonoSeguridad(),
                    seguridad.getDpiSeguridad())) {
                throw new RuntimeException("Ya existe un empleado de seguridad con estos datos");
            }

            return seguridadRepository.save(seguridad);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Seguridad updateSeguridad(Integer id, Seguridad seguridad) {
        Seguridad existingSeguridad = seguridadRepository.findById(id).orElseThrow(() -> new RuntimeException("El empleado de seguridad no existe"));

        if (seguridadRepository.existsByNombreAndPuestoAndJornadaAndSalarioAndTelefonoSeguridadAndDpiSeguridad(
                seguridad.getNombre(),
                seguridad.getPuesto(),
                seguridad.getJornada(),
                seguridad.getSalario(),
                seguridad.getTelefonoSeguridad(),
                seguridad.getDpiSeguridad())) {
            throw new RuntimeException("Ya existe un empleado de seguridad con estos datos");
        }

        existingSeguridad.setNombre(seguridad.getNombre());
        existingSeguridad.setPuesto(seguridad.getPuesto());
        existingSeguridad.setJornada(seguridad.getJornada());
        existingSeguridad.setSalario(seguridad.getSalario());
        existingSeguridad.setTelefonoSeguridad(seguridad.getTelefonoSeguridad());
        existingSeguridad.setDpiSeguridad(seguridad.getDpiSeguridad());

        return seguridadRepository.save(existingSeguridad);
    }

    @Override
    public void deleteSeguridad(Integer id) {
        if (!seguridadRepository.existsById(id)) {
            throw new RuntimeException("Este id no existe");
        }
        seguridadRepository.deleteById(id);
    }

    @Override
    public Seguridad login(String nombreSeguridad, String dpiSeguridad) {
        return seguridadRepository.findByNombreAndDpiSeguridad(nombreSeguridad, dpiSeguridad);
    }
}