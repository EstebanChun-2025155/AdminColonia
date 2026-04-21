package Control.Colonia.Service;

import Control.Colonia.Entity.Casa;
import Control.Colonia.Repository.CasaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CasaServiceImplements implements CasaService {
    private final CasaRepository casaRepository;

    public CasaServiceImplements(CasaRepository casaRepository) {
        this.casaRepository = casaRepository;
    }

    @Override
    public List<Casa> getAllCasa() { return casaRepository.findAll(); }

    @Override
    public Casa getCasaById(Integer id) { return casaRepository.getReferenceById(id); }

    @Override
    public Casa saveCasa(Casa casa) throws RuntimeException {
            return casaRepository.save(casa);
    }

    @Override
    public Casa updateCasa(Integer id, Casa casa) {
        Casa existingCasa = casaRepository.findById(id).orElseThrow(() -> new RuntimeException("La casa no existe"));
        return casaRepository.save(existingCasa);
    }

    @Override
    public void deleteCasa(Integer id) {
        if(!casaRepository.existsById(id)){
            throw new RuntimeException("Este id no existe");
        }
        casaRepository.deleteById(id);
    }
}