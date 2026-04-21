package com.Administracion.Colonia.Service;

import com.Administracion.Colonia.Entity.Pago;
import com.Administracion.Colonia.Repository.PagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoServiceImplements implements PagoService{
    private final PagoRepository pagoRepository;

    public PagoServiceImplements(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public List<Pago> getAllPago() { return pagoRepository.findAll(); }

    @Override
    public Pago getPagoById(Integer id) { return pagoRepository.getReferenceById(id); }

    @Override
    public Pago savePago(Pago pago) throws RuntimeException {
        try {
            if (pagoRepository.existsByIdResidenteAndClasificacionPagoAndMontoAndFechaPagoAndMetodoAndReferencia(
                    pago.getIdResidente(),
                    pago.getClasificacionPago(),
                    pago.getMonto(),
                    pago.getFechaPago(),
                    pago.getMetodo(),
                    pago.getReferencia())){
                throw new RuntimeException("Ya existe un pago con estos datos");
            }

            return pagoRepository.save(pago);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Pago updatePago(Integer id, Pago pago) {
        Pago existingPago = pagoRepository.findById(id).orElseThrow(() -> new RuntimeException("El pago no existe"));

        if (pagoRepository.existsByIdResidenteAndClasificacionPagoAndMontoAndFechaPagoAndMetodoAndReferencia(
                pago.getIdResidente(),
                pago.getClasificacionPago(),
                pago.getMonto(),
                pago.getFechaPago(),
                pago.getMetodo(),
                pago.getReferencia())){
            throw new RuntimeException("Ya existe un pago con estos datos");
        }

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
        if(!pagoRepository.existsById(id)){
            throw new RuntimeException("Este id no existe");
        }
        pagoRepository.deleteById(id);
    }
}