package com.Administracion.Colonia.Service;

import com.Administracion.Colonia.Entity.Multa;
import com.Administracion.Colonia.Repository.MultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultaServiceImplements implements MultaService {
    private final MultaRepository multaRepository;

    public MultaServiceImplements(MultaRepository multaRepository) {
        this.multaRepository = multaRepository;
    }

    @Override
    public List<Multa> getAllMulta() { return multaRepository.findAll(); }

    @Override
    public Multa getMultaById(Integer id) { return multaRepository.getReferenceById(id); }

    @Override
    public Multa saveMulta(Multa multa) throws RuntimeException {
        try {
            if (multaRepository.existsByMontoAndDescripcionAndFechaEmisionAndEstadoAndTipoPersona(
                    multa.getMonto(),
                    multa.getDescripcion(),
                    multa.getFechaEmision(),
                    multa.getEstado(),
                    multa.getTipoPersona())){
                throw new RuntimeException("Ya existe una multa con estos datos");
            }

            return multaRepository.save(multa);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Multa updateMulta(Integer id, Multa multa) {
        Multa existingMulta = multaRepository.findById(id).orElseThrow(() -> new RuntimeException("La multa no existe"));

        if (multaRepository.existsByMontoAndDescripcionAndFechaEmisionAndEstadoAndTipoPersona(
                multa.getMonto(),
                multa.getDescripcion(),
                multa.getFechaEmision(),
                multa.getEstado(),
                multa.getTipoPersona())){
            throw new RuntimeException("Ya existe una multa con estos datos");
        }

        existingMulta.setMonto(multa.getMonto());
        existingMulta.setDescripcion(multa.getDescripcion());
        existingMulta.setFechaEmision(multa.getFechaEmision());
        existingMulta.setEstado(multa.getEstado());
        existingMulta.setTipoPersona(multa.getTipoPersona());

        return multaRepository.save(existingMulta);
    }

    @Override
    public void deleteMulta(Integer id) {
        if(!multaRepository.existsById(id)){
            throw new RuntimeException("Este id no existe");
        }
        multaRepository.deleteById(id);
    }
}