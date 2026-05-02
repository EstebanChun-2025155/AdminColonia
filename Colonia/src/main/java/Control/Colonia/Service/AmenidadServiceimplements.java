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

    @Override
    public List<Amenidad> getAllAmenidad() {
        return amenidadRepository.findAll();
    }

    @Override
    public Amenidad getAmenidadById(Integer id) {
        return amenidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amenidad no encontrada con el ID: " + id));
    }

    @Override
    public Amenidad saveAmenidad(Amenidad amenidad) {
        // Forzar minúsculas antes de validar y guardar
        if (amenidad.getEstado() != null) {
            amenidad.setEstado(amenidad.getEstado().toLowerCase());
        }

        if (amenidadRepository.existsByNombreAmenidadAndHorarioUsoAndCostoUsoAndEstadoAndCapacidad(
                amenidad.getNombreAmenidad(),
                amenidad.getHorarioUso(),
                amenidad.getCostoUso(),
                amenidad.getEstado(),
                amenidad.getCapacidad())) {
            throw new RuntimeException("Ya existe una amenidad con estos datos");
        }

        return amenidadRepository.save(amenidad);
    }

    @Override
    public Amenidad updateAmenidad(Integer id, Amenidad amenidad) {
        // 1. Verificar existencia
        Amenidad existingAmenidad = amenidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La amenidad no existe"));

        // 2. Actualizar datos (Sin el IF de existsBy para evitar el bloqueo del mismo registro)
        existingAmenidad.setNombreAmenidad(amenidad.getNombreAmenidad());
        existingAmenidad.setHorarioUso(amenidad.getHorarioUso());
        existingAmenidad.setCostoUso(amenidad.getCostoUso());
        // Forzamos a minúsculas para mantener consistencia en la BD
        existingAmenidad.setEstado(amenidad.getEstado().toLowerCase());
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