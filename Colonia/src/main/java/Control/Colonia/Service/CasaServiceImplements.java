package Control.Colonia.Service;

import Control.Colonia.Entity.Casa;
import Control.Colonia.Repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CasaServiceImplements implements CasaService {
    @Autowired
    private CasaRepository repo;

    @Override
    public List<Casa> getAllCasa() { return repo.findAll(); }

    @Override
    public Casa getCasaById(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Casa no encontrada"));
    }

    @Override
    public Casa saveCasa(Casa casa) throws RuntimeException {
            return repo.save(casa);
    }

    @Override
    public Casa updateCasa(Integer id, Casa casa) {
        Casa existingCasa = repo.findById(id).orElseThrow(() -> new RuntimeException("La casa no existe"));

        existingCasa.setNoDeCasa(casa.getNoDeCasa());
        existingCasa.setDireccion(casa.getDireccion());
        existingCasa.setEstado(casa.getEstado());
        existingCasa.setPropietario(casa.getPropietario());
        existingCasa.setPrecioCasa(casa.getPrecioCasa());

        return repo.save(existingCasa);
    }

    @Override
    public void deleteCasa(Integer id) {
        repo.deleteById(id);
    }
}