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
        try {
            if (repo.existsByNoDeCasaAndDireccionAndEstadoAndPropietarioAndPrecioCasa(
                    casa.getNoDeCasa(),
                    casa.getDireccion(),
                    casa.getEstado(),
                    casa.getPropietario(),
                    casa.getPrecioCasa())){
                throw new RuntimeException("Ya existe una casa con estos datos");
            }

            return repo.save(casa);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Casa updateCasa(Integer id, Casa casa) {
        Casa existingCasa = repo.findById(id).orElseThrow(() -> new RuntimeException("La casa no existe"));

        if (repo.existsByNoDeCasaAndDireccionAndEstadoAndPropietarioAndPrecioCasa(
                casa.getNoDeCasa(),
                casa.getDireccion(),
                casa.getEstado(),
                casa.getPropietario(),
                casa.getPrecioCasa())){
            throw new RuntimeException("Ya existe una casa con estos datos");
        }

        existingCasa.setNoDeCasa(casa.getNoDeCasa());
        existingCasa.setDireccion(casa.getDireccion());
        existingCasa.setEstado(casa.getEstado());
        existingCasa.setPropietario(casa.getPropietario());
        existingCasa.setPrecioCasa(casa.getPrecioCasa());

        return repo.save(existingCasa);
    }

    @Override
    public void deleteCasa(Integer id) {
        if(!repo.existsById(id)){
            throw new RuntimeException("Este id no existe");
        }
        repo.deleteById(id);
    }
}