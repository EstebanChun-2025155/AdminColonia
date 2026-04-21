package com.Administracion.Colonia.Service;

import com.Administracion.Colonia.Entity.Pago;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PagoService {
    List<Pago> getAllPago();
    Pago getPagoById(Integer id);
    Pago savePago(Pago pago) throws RuntimeException;
    Pago updatePago(Integer id, Pago pago);
    void deletePago(Integer id);
}