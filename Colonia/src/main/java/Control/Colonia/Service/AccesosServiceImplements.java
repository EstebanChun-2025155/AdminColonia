package Control.Colonia.Service;

import Control.Colonia.Entity.Accesos;
import Control.Colonia.Repository.AccesosRepository;
import org.springframework.stereotype.Service;

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
        return accesosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Acceso no encontrado"));
    }

    @Override
    public Accesos saveAcceso(Accesos accesos) {
        if (accesosRepository.existsByTipoPersonaAndIdSeguridadAndHoraEntradaAndHoraSalida(
                accesos.getTipoPersona(),
                accesos.getIdSeguridad(),
                accesos.getHoraEntrada(),
                accesos.getHoraSalida())) {

            throw new RuntimeException("Ya existe un acceso con esos datos");
        }

        return accesosRepository.save(accesos);
    }

    @Override
    public Accesos updateAcceso(Integer id, Accesos accesos) {

        Accesos existente = accesosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El acceso no existe"));

        existente.setTipoPersona(accesos.getTipoPersona());
        existente.setIdSeguridad(accesos.getIdSeguridad());
        existente.setHoraEntrada(accesos.getHoraEntrada());
        existente.setHoraSalida(accesos.getHoraSalida());

        return accesosRepository.save(existente);
    }

    @Override
    public void deleteAcceso(Integer id) {
        if (!accesosRepository.existsById(id)) {
            throw new RuntimeException("Este acceso no existe");
        }
        accesosRepository.deleteById(id);
    }
}