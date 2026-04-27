package com.Administracion.Colonia.service;

import com.Administracion.Colonia.entity.Vehiculo;
import com.Administracion.Colonia.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoServiceimplements implements VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoServiceimplements(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public List<Vehiculo> getAllVehiculo() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Vehiculo getVehiculoById(Integer id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
    }

    @Override
    public Vehiculo saveVehiculo(Vehiculo vehiculo) throws RuntimeException {
        try {
            if (vehiculoRepository.existsByIdVehiculoAndPlacaAndMarcaModeloAndColorAndPropietarioAndIdCasa(
                    vehiculo.getIdVehiculo(),
                    vehiculo.getPlaca(),
                    vehiculo.getMarcaModelo(),
                    vehiculo.getColor(),
                    vehiculo.getPropietario(),
                    vehiculo.getIdCasa())) {

                throw new RuntimeException("Ya existe un vehículo con estos datos");
            }

            return vehiculoRepository.save(vehiculo);

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Vehiculo updateVehiculo(Integer id, Vehiculo vehiculo) {

        Vehiculo existingVehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El vehículo no existe"));

        if (vehiculoRepository.existsByIdVehiculoAndPlacaAndMarcaModeloAndColorAndPropietarioAndIdCasa(
                vehiculo.getIdVehiculo(),
                vehiculo.getPlaca(),
                vehiculo.getMarcaModelo(),
                vehiculo.getColor(),
                vehiculo.getPropietario(),
                vehiculo.getIdCasa())) {

            throw new RuntimeException("Ya existe un vehículo con estos datos");
        }

        existingVehiculo.setIdVehiculo(vehiculo.getIdVehiculo());
        existingVehiculo.setPlaca(vehiculo.getPlaca());
        existingVehiculo.setMarcaModelo(vehiculo.getMarcaModelo());
        existingVehiculo.setColor(vehiculo.getColor());
        existingVehiculo.setPropietario(vehiculo.getPropietario());
        existingVehiculo.setIdCasa(vehiculo.getIdCasa());

        return vehiculoRepository.save(existingVehiculo);
    }

    @Override
    public void deleteVehiculo(Integer id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new RuntimeException("Este id no existe");
        }

        vehiculoRepository.deleteById(id);
    }
}