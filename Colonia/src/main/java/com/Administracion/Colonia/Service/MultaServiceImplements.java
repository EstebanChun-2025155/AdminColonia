package com.Administracion.Colonia.Service;

import com.Administracion.Colonia.Entity.Multa;
import com.Administracion.Colonia.Repository.MultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultaServiceImplements implements MultaService {

    @Autowired
    private MultaRepository multaRepository;

    @Override
    public List<Multa> getAllMulta() { return multaRepository.findAll(); }

    @Override
    public Multa getMultaById(Integer id) {
        return multaRepository.findById(id).orElseThrow(() -> new RuntimeException("Multa no encontrada"));
    }

    @Override
    public Multa saveMulta(Multa multa) throws RuntimeException {
        return multaRepository.save(multa);
    }

    @Override
    public Multa updateMulta(Integer id, Multa multa) {
        Multa existingMulta = multaRepository.findById(id).orElseThrow(() -> new RuntimeException("La multa no existe"));


        existingMulta.setMonto(multa.getMonto());
        existingMulta.setDescripcion(multa.getDescripcion());
        existingMulta.setFechaEmision(multa.getFechaEmision());
        existingMulta.setEstado(multa.getEstado());
        existingMulta.setTipoPersona(multa.getTipoPersona());

        return multaRepository.save(existingMulta);
    }

    @Override
    public void deleteMulta(Integer id) {
        multaRepository.deleteById(id);
    }
}