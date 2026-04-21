package com.Administracion.Colonia.Service;

import com.Administracion.Colonia.Entity.Multa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MultaService {
    List<Multa> getAllMulta();
    Multa getMultaById(Integer id);
    Multa saveMulta(Multa multa) throws RuntimeException;
    Multa updateMulta(Integer id, Multa multa);
    void deleteMulta(Integer id);
}