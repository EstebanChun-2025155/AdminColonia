package Control.Colonia.Service;


import Control.Colonia.Service.VisitasService;
import com.Administracion.Colonia.Entity.Visitas;
import com.Administracion.Colonia.Repository.VisitasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitasServiceImplements implements VisitasService {

    private final VisitasRepository visitasRepository;

    public VisitasServiceImplements(VisitasRepository visitasRepository) {
        this.visitasRepository = visitasRepository;
    }

    @Override
    public List<Visitas> getAllVisitas() {
        return visitasRepository.findAll();
    }

    @Override
    public Visitas getVisitasById(Integer id) {
        Visitas visitas = visitasRepository.findById(id).orElse(null);
        if(visitas == null) {
            throw new IllegalArgumentException("Visita no encontrada");
        }
        return visitasRepository.findById(id).orElse(null);
    }

    @Override
    public Visitas saveVisitas(Visitas visitas) throws RuntimeException {
        try {
            if (visitasRepository.existsByNombreVisitaAndDocumentoAndPlacaAndMotivoAndIdCasa(
                    visitas.getNombreVisita(),
                    visitas.getDocumento(),
                    visitas.getPlaca(),
                    visitas.getMotivo(),
                    visitas.getIdCasa())){
                throw new RuntimeException("Ya existe una visita con estos datos");
            }
            return visitasRepository.save(visitas);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Visitas updateVisitas(Integer id, Visitas visitas) {
        Visitas visitas1= visitasRepository.findById(id).orElseThrow(() -> new RuntimeException("La Visita no existe")) ;
        if(visitasRepository.existsByNombreVisitaAndDocumentoAndPlacaAndMotivoAndIdCasa(
                visitas.getNombreVisita(),
                visitas.getDocumento(),
                visitas.getPlaca(),
                visitas.getMotivo(),
                visitas.getIdCasa())){
            throw new RuntimeException("Ya existe una visita con estos datos");
        }
        visitas1.setNombreVisita(visitas.getNombreVisita());
        visitas1.setDocumento(visitas.getDocumento());
        visitas1.setPlaca(visitas.getPlaca());
        visitas1.setMotivo(visitas.getMotivo());
        visitas1.setIdCasa(visitas.getIdCasa());

        return visitasRepository.save(visitas1);
    }

    @Override
    public void deleteVisitas(Integer id) {
        Visitas visitas = visitasRepository.findById(id).orElse(null);
        if(!visitasRepository.existsById(id)) {
            throw new IllegalArgumentException("Este id no existe");
        }
        visitasRepository.deleteById(id);
    }
}