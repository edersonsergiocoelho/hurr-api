package br.com.escconsulting.service;

import br.com.escconsulting.model.Vehicle;
import br.com.escconsulting.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> findByVehicleBrandVehicleBrandId(UUID brandId) {
        return vehicleRepository.findByVehicleBrandVehicleBrandId(brandId);
    }

    public Optional<Vehicle> findById(UUID id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(UUID id, Vehicle vehicle) {
        Optional<Vehicle> existingVehicleOptional = vehicleRepository.findById(id);
        if (existingVehicleOptional.isPresent()) {
            Vehicle existingVehicle = existingVehicleOptional.get();
            existingVehicle.setVehicleName(vehicle.getVehicleName());
            existingVehicle.setVehicleBrand(vehicle.getVehicleBrand());
            // Only update timestamps if provided
            if (vehicle.getCreatedDate() != null) {
                existingVehicle.setCreatedDate(vehicle.getCreatedDate());
            }
            if (vehicle.getModifiedDate() != null) {
                existingVehicle.setModifiedDate(vehicle.getModifiedDate());
            }
            existingVehicle.setEnabled(vehicle.getEnabled());
            return vehicleRepository.save(existingVehicle);
        } else {
            return null;
        }
    }

    public void deleteById(UUID id) {
        vehicleRepository.deleteById(id);
    }
}