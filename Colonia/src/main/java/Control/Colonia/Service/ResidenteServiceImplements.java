package Control.Colonia.Service;

import Control.Colonia.Entity.Residente;
import Control.Colonia.Repository.ResidenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidenteServiceImplements implements ResidenteService {
    private final ResidenteRepository residenteRepository;

    public ResidenteServiceImplements(ResidenteRepository residenteRepository) {
        this.residenteRepository = residenteRepository;
    }

    @Override
    public List<Residente> getAllResidente() { return residenteRepository.findAll();}

    @Override
    public Residente getResidenteById(Integer id) { return residenteRepository.getReferenceById(id); }

    @Override
    public Residente saveResidente(Residente residente) throws RuntimeException {
            return residenteRepository.save(residente);
    }

    @Override
    public Residente updateResidente(Integer id, Residente residente) {
        Residente existingResidente = residenteRepository.findById(id).orElseThrow(()-> new RuntimeException("Esta Casa no existe"));
        return residenteRepository.save(existingResidente);
    }

    @Override
    public void deleteResidente(Integer id) {
        if(!residenteRepository.existsById(id)){
            throw new RuntimeException("Este id no existe");
        }
        residenteRepository.deleteById(id);
    }
}
