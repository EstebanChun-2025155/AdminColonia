package Control.Colonia.Service;

import Control.Colonia.Entity.Residente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResidenteService {
    List<Residente> getAllResidente();
    Residente getResidenteById(Integer id);
    Residente saveResidente(Residente residente) throws RuntimeException;
    Residente updateResidente(Integer id, Residente residente);
    void deleteResidente(Integer id);
}