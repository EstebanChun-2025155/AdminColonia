package Control.Colonia.Service;

import Control.Colonia.Entity.Vehiculo;

import java.util.List;

public interface VehiculoService {

    List<Vehiculo> getAllVehiculo();

    Vehiculo getVehiculoById(Integer id);

    Vehiculo saveVehiculo(Vehiculo vehiculo) throws RuntimeException;

    Vehiculo updateVehiculo(Integer id, Vehiculo vehiculo);

    void deleteVehiculo(Integer id);
}