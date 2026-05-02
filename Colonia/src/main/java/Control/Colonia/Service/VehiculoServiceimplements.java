package Control.Colonia.Service;

import Control.Colonia.Entity.Vehiculo;
import Control.Colonia.Repository.VehiculoRepository;
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
    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        // 1. Validar placa duplicada
        if (vehiculoRepository.existsByPlaca(vehiculo.getPlaca())) {
            throw new RuntimeException("La placa ya está registrada");
        }
        // 2. Intentar guardar (Si el ID de casa no existe, el try-catch del Controller lo atrapará)
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Vehiculo updateVehiculo(Integer id, Vehiculo vehiculo) {
        Vehiculo existente = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        existente.setPlaca(vehiculo.getPlaca());
        existente.setMarcaModelo(vehiculo.getMarcaModelo());
        existente.setColor(vehiculo.getColor());
        existente.setPropietario(vehiculo.getPropietario());
        existente.setIdCasa(vehiculo.getIdCasa());

        return vehiculoRepository.save(existente);
    }

    @Override
    public void deleteVehiculo(Integer id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new RuntimeException("Este id no existe");
        }
        vehiculoRepository.deleteById(id);
    }


}