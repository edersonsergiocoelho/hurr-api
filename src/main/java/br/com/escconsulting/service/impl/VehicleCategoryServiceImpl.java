package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.VehicleCategory;
import br.com.escconsulting.repository.VehicleCategoryRepository;
import br.com.escconsulting.service.VehicleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleCategoryServiceImpl implements VehicleCategoryService {

    private final VehicleCategoryRepository vehicleCategoryRepository;

    @Autowired
    public VehicleCategoryServiceImpl(VehicleCategoryRepository vehicleCategoryRepository) {
        this.vehicleCategoryRepository = vehicleCategoryRepository;
    }

    @Override
    public List<VehicleCategory> getAllVehicleCategories() {
        return vehicleCategoryRepository.findAll();
    }

    @Override
    public VehicleCategory getVehicleCategoryById(UUID id) {
        return vehicleCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public VehicleCategory createVehicleCategory(VehicleCategory vehicleCategory) {
        return vehicleCategoryRepository.save(vehicleCategory);
    }

    @Override
    public VehicleCategory updateVehicleCategory(UUID id, VehicleCategory vehicleCategory) {
        if (vehicleCategoryRepository.existsById(id)) {
            vehicleCategory.setVehicleCategoryId(id);
            return vehicleCategoryRepository.save(vehicleCategory);
        }
        return null;
    }

    @Override
    public void deleteVehicleCategory(UUID id) {
        vehicleCategoryRepository.deleteById(id);
    }
}