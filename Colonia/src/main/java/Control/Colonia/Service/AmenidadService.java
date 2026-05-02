package Control.Colonia.Service;

import Control.Colonia.Entity.Amenidad;

import java.util.List;

public interface AmenidadService {

    List<Amenidad> getAllAmenidad();

    Amenidad getAmenidadById(Integer id);

    Amenidad saveAmenidad(Amenidad amenidad) throws RuntimeException;

    Amenidad updateAmenidad(Integer id, Amenidad amenidad);

    void deleteAmenidad(Integer id);
}