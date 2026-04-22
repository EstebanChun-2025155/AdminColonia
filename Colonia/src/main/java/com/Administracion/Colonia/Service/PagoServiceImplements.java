package com.Administracion.Colonia.Service;

import com.Administracion.Colonia.Entity.Pago;
import com.Administracion.Colonia.Repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServiceImplements implements PagoService{

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public List<Pago> getAllPago() { return pagoRepository.findAll(); }

    @Override
    public Pago getPagoById(Integer id) {
        return pagoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pago no encontrada"));
    }

    @Override
    public Pago savePago(Pago pago) throws RuntimeException {
        return pagoRepository.save(pago);
    }

    @Override
    public Pago updatePago(Integer id, Pago pago) {
        Pago existingPago = pagoRepository.findById(id).orElseThrow(() -> new RuntimeException("El pago no existe"));

        existingPago.setIdResidente(pago.getIdResidente());
        existingPago.setClasificacionPago(pago.getClasificacionPago());
        existingPago.setMonto(pago.getMonto());
        existingPago.setFechaPago(pago.getFechaPago());
        existingPago.setMetodo(pago.getMetodo());
        existingPago.setReferencia(pago.getReferencia());

        return pagoRepository.save(existingPago);
    }

    @Override
    public void deletePago(Integer id) {
        pagoRepository.deleteById(id);
    }
}