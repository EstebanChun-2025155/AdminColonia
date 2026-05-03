package com.Administracion.Colonia.service;

import com.Administracion.Colonia.entity.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {

    List<Vehiculo> getAllVehiculo();

    Vehiculo getVehiculoById(Integer id);

    Vehiculo saveVehiculo(Vehiculo vehiculo) throws RuntimeException;

    Vehiculo updateVehiculo(Integer id, Vehiculo vehiculo);

    void deleteVehiculo(Integer id);

    Optional<Vehiculo> buscarPorId(Integer id);

    boolean existePlaca(String placa);
}