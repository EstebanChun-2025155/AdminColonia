package Control.Colonia.Service;

import Control.Colonia.Entity.Amenidad;
import Control.Colonia.Repository.AmenidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenidadServiceimplements implements AmenidadService {

    private final AmenidadRepository amenidadRepository;

    public AmenidadServiceimplements(AmenidadRepository amenidadRepository) {
        this.amenidadRepository = amenidadRepository;
    }

    private Double obtenerCostoAmenidad(String nombreAmenidad) {
        if (nombreAmenidad == null) {
            throw new RuntimeException("Debe seleccionar una amenidad válida");
        }

        return switch (nombreAmenidad.toLowerCase()) {
            case "salon social" -> 250.00;
            case "piscina" -> 0.00;
            case "cancha deportiva" -> 50.00;
            case "lounge de estudio" -> 0.00;
            case "terraza" -> 75.00;
            case "cinema" -> 0.00;
            default -> throw new RuntimeException("La amenidad seleccionada no tiene costo asignado");
        };
    }

    @Override
    public List<Amenidad> getAllAmenidad() {
        return amenidadRepository.findAll();
    }

    @Override
    public List<Amenidad> buscarPorResidente(Integer idResidente) {
        return amenidadRepository.findByIdResidente(idResidente);
    }

    @Override
    public Amenidad getAmenidadById(Integer id) {
        return amenidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amenidad no encontrada con el ID: " + id));
    }

    @Override
    public Amenidad saveAmenidad(Amenidad amenidad) {

        amenidad.setCostoUso(obtenerCostoAmenidad(amenidad.getNombreAmenidad()));

        if (amenidadRepository.existsByNombreAmenidadAndHorarioAndFecha(
                amenidad.getNombreAmenidad(),
                amenidad.getHorario(),
                amenidad.getFecha())){
            throw new RuntimeException("Ya existe una amenidad con estos datos");
        }

        return amenidadRepository.save(amenidad);
    }

    @Override
    public Amenidad updateAmenidad(Integer id, Amenidad amenidad) {
        Amenidad existingAmenidad = amenidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La amenidad no existe"));

        if (amenidadRepository.existsByNombreAmenidadAndHorarioAndFechaAndIdAmenidadNot(
                amenidad.getNombreAmenidad(),
                amenidad.getHorario(),
                amenidad.getFecha(),
                id)) {
            throw new RuntimeException("Ya existe una amenidad registrada con esa fecha y horario");
        }

        amenidad.setCostoUso(obtenerCostoAmenidad(amenidad.getNombreAmenidad()));

        existingAmenidad.setIdResidente(amenidad.getIdResidente());
        existingAmenidad.setNombreAmenidad(amenidad.getNombreAmenidad());
        existingAmenidad.setHorario(amenidad.getHorario());
        existingAmenidad.setFecha(amenidad.getFecha());
        existingAmenidad.setCostoUso(amenidad.getCostoUso());
        existingAmenidad.setEstado(amenidad.getEstado());
        existingAmenidad.setCapacidad(amenidad.getCapacidad());

        return amenidadRepository.save(existingAmenidad);
    }

    @Override
    public void deleteAmenidad(Integer id) {
        if (!amenidadRepository.existsById(id)) {
            throw new RuntimeException("Este id no existe");
        }
        amenidadRepository.deleteById(id);
    }
}