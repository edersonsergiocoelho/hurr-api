package br.com.escconsulting.service;

import br.com.escconsulting.entity.VehicleBrand;
import br.com.escconsulting.repository.VehicleBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleBrandService {

    @Autowired
    private VehicleBrandRepository vehicleBrandRepository;

    public List<VehicleBrand> getAllBrands() {
        return vehicleBrandRepository.findAll();
    }

    public VehicleBrand getVehicleBrandById(UUID vehicleBrandId) {
        return vehicleBrandRepository.findById(vehicleBrandId).orElseThrow();
    }

    public VehicleBrand createVehicleBrand(VehicleBrand brand) {
        return vehicleBrandRepository.save(brand);
    }

    public VehicleBrand updateVehicleBrand(UUID vehicleBrandId, VehicleBrand vehicleBrand) {
        VehicleBrand existingBrand = vehicleBrandRepository.findById(vehicleBrandId).orElseThrow();
        existingBrand.setVehicleBrandName(vehicleBrand.getVehicleBrandName());
        existingBrand.setEnabled(vehicleBrand.getEnabled());
        return vehicleBrandRepository.save(existingBrand);
    }

    public void deleteVehicleBrand(UUID vehicleBrandId) {
        vehicleBrandRepository.deleteById(vehicleBrandId);
    }
}