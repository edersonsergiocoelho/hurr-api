package br.com.escconsulting.service;

import br.com.escconsulting.entity.Vehicle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleService {

    List<Vehicle> findAll();

    List<Vehicle> findByVehicleBrandVehicleBrandId(UUID brandId);

    Optional<Vehicle> findById(UUID vehicleId);

    Vehicle save(Vehicle vehicle);

    Vehicle updateVehicle(UUID vehicleId, Vehicle vehicle);

    void delete(UUID vehicleId);
}