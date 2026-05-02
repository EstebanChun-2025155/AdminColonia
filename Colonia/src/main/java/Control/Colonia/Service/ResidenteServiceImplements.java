package Control.Colonia.Service;

import Control.Colonia.Entity.Residente;
import Control.Colonia.Repository.ResidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidenteServiceImplements implements ResidenteService {
    @Autowired
    private ResidenteRepository residenteRepository;

    @Override
    public List<Residente> getAllResidente() { return residenteRepository.findAll();}

    @Override
    public Residente getResidenteById(Integer id) {
        return residenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Este residente no existe")); }

    @Override
    public Residente saveResidente(Residente residente) throws RuntimeException {
        try {
            if(residenteRepository.existsByNombreResidenteAndDpiResidenteAndTelefonoResidenteAndPosicionAndIdCasa(
                    residente.getNombreResidente(),
                    residente.getDpiResidente(),
                    residente.getTelefonoResidente(),
                    residente.getPosicion(),
                    residente.getIdCasa())){

                throw new RuntimeException("Ya existe una residente con estos datos");
            }
            return residenteRepository.save(residente);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Residente updateResidente(Integer id, Residente residente) {
        Residente existingResidente = residenteRepository.findById(id).orElseThrow(()-> new RuntimeException("Este Residente no existe"));

        if(residenteRepository.existsByNombreResidenteAndDpiResidenteAndTelefonoResidenteAndPosicionAndIdCasa(
                residente.getNombreResidente(),
                residente.getDpiResidente(),
                residente.getTelefonoResidente(),
                residente.getPosicion(),
                residente.getIdCasa())){

            throw new RuntimeException("Ya existe una residente con estos datos");
        }

        existingResidente.setNombreResidente(residente.getNombreResidente());
        existingResidente.setDpiResidente(residente.getDpiResidente());
        existingResidente.setTelefonoResidente(residente.getTelefonoResidente());
        existingResidente.setPosicion(residente.getPosicion());
        existingResidente.setIdCasa(residente.getIdCasa());

        return residenteRepository.save(existingResidente);
    }

    @Override
    public void deleteResidente(Integer id) {
        if(!residenteRepository.existsById(id)){
            throw new RuntimeException("Este id no existe");
        }
        residenteRepository.deleteById(id);
    }

    @Override
    public Residente login(String nombreResidente, String dpiResidente) {
        return residenteRepository.findByNombreResidenteAndDpiResidente(nombreResidente, dpiResidente);
    }
}
