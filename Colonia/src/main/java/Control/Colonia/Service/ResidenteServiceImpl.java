package Control.Colonia.Service;

import Control.Colonia.Entity.Residente;
import Control.Colonia.Repository.ResidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenteServiceImpl implements ResidenteService {

    @Autowired
    private ResidenteRepository residenteRepository;

    @Override
    public Residente login(String nombreResidente, String dpiResidente) {
        return residenteRepository.findByNombreResidenteAndDpiResidente(nombreResidente, dpiResidente);
    }
}