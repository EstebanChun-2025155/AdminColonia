package com.Administracion.Colonia.service;

import com.Administracion.Colonia.entity.Amenidad;
import com.Administracion.Colonia.entity.Vehiculo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehiculoService {

    List<Vehiculo> getAllVehiculo();

    Vehiculo getVehiculoByid(Integer id);

    Vehiculo saveVehiculo(Vehiculo vehiculo);

    Vehiculo updateVehiculo(Integer id, Vehiculo vehiculo);

    void deleteVehiculo(Integer id);
}
