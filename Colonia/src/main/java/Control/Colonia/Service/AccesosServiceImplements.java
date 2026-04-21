package Control.Colonia.Service;

import Control.Colonia.Entity.Accesos;
import Control.Colonia.Repository.AccesosRepository;
import org.springframework.stereotype.Service;
import Control.Colonia.Service.AccesosService;
import java.util.List;



@Service
public class AccesosServiceImplements implements AccesosService {
    private final AccesosRepository accesosRepository;

    public AccesosServiceImplements(AccesosRepository accesosRepository) {
        this.accesosRepository = accesosRepository;
    }

    @Override
    public List<Accesos> getAllAccesos() {
        return accesosRepository.findAll();
    }

    @Override
    public Accesos getAccesosById(Integer id) {
        Accesos accesos = accesosRepository.findById(id).orElse(null);
        if(accesos == null) {
            throw new IllegalArgumentException("Acceso no encontrado");
        }
        return accesosRepository.findById(id).orElse(null);
    }

    @Override
    public Accesos saveAcceso(Accesos accesos) throws RuntimeException {
        try {
            if (accesosRepository.existsByTipoPersonaAndIdSeguridadAndHoraEntradaAndHoraSalida(
                    accesos.getTipoPersona(),
                    accesos.getIdSeguridad(),
                    accesos.getHoraEntrada(),
                    accesos.getHoraSalida())){
                throw new RuntimeException("Ya existe un acceso con esos datos");
            }
            return accesosRepository.save(accesos);
        } catch (RuntimeException e) {
            throw new  RuntimeException(e.getMessage());
        }
    }

    @Override
    public Accesos updateAcceso(Integer id, Accesos accesos) {
        Accesos accesos1 = accesosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El acceso no existe"));

        if(accesosRepository.existsByTipoPersonaAndIdSeguridadAndHoraEntradaAndHoraSalida(
                accesos.getTipoPersona(),
                accesos.getIdSeguridad(),
                accesos.getHoraEntrada(),
                accesos.getHoraSalida())){
            throw new RuntimeException("Ya existe un acceso con esos datos");
        }

        accesos1.setIdSeguridad(accesos.getIdSeguridad());
        accesos1.setTipoPersona(accesos.getTipoPersona());
        accesos1.setHoraEntrada(accesos.getHoraEntrada());
        accesos1.setHoraSalida(accesos.getHoraSalida());
        return accesosRepository.save(accesos1);
    }

    @Override
    public void deleteAccesos(Integer id) {
        Accesos accesos = accesosRepository.findById(id).orElse(null);
        if(!accesosRepository.existsById(id)){
            throw new IllegalArgumentException("Este acceso no existe");
        }
        accesosRepository.deleteById(id);
    }
}