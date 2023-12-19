package br.com.escconsulting.service;

import br.com.escconsulting.entity.VehicleCategory;

import java.util.List;
import java.util.UUID;

public interface VehicleCategoryService {
    List<VehicleCategory> getAllVehicleCategories();

    VehicleCategory getVehicleCategoryById(UUID id);

    VehicleCategory createVehicleCategory(VehicleCategory vehicleCategory);

    VehicleCategory updateVehicleCategory(UUID id, VehicleCategory vehicleCategory);

    void deleteVehicleCategory(UUID id);
}