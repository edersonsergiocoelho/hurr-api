package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.Vehicle;
import br.com.escconsulting.repository.VehicleRepository;
import br.com.escconsulting.service.VehicleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    @Transactional
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    @Transactional
    public List<Vehicle> findByVehicleBrandVehicleBrandId(UUID brandId) {
        return vehicleRepository.findByVehicleBrandVehicleBrandId(brandId);
    }

    @Override
    @Transactional
    public Optional<Vehicle> findById(UUID vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    @Override
    @Transactional
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public Vehicle updateVehicle(UUID vehicleId, Vehicle vehicle) {

        Optional<Vehicle> existingVehicleOptional = vehicleRepository.findById(vehicleId);

        if (existingVehicleOptional.isPresent()) {

            Vehicle existingVehicle = existingVehicleOptional.get();
            existingVehicle.setVehicleName(vehicle.getVehicleName());
            existingVehicle.setVehicleBrand(vehicle.getVehicleBrand());

            existingVehicle.setModifiedDate(vehicle.getModifiedDate());

            existingVehicle.setEnabled(vehicle.getEnabled());
            return vehicleRepository.save(existingVehicle);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(UUID vehicleId) {
        findById(vehicleId).ifPresent(vehicleRepository::delete);
    }
}