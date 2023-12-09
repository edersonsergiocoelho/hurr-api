package br.com.escconsulting.service;

import br.com.escconsulting.model.VehicleModel;
import br.com.escconsulting.repository.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleModelService {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    public List<VehicleModel> findAll() {
        return vehicleModelRepository.findAll();
    }

    public Optional<VehicleModel> findById(UUID id) {
        return vehicleModelRepository.findById(id);
    }

    public List<VehicleModel> findByVehicleVehicleId(UUID vehicleId) {
        return vehicleModelRepository.findByVehicleVehicleId(vehicleId);
    }

    public VehicleModel create(VehicleModel vehicleModel) {
        return vehicleModelRepository.save(vehicleModel);
    }

    public VehicleModel updateVehicleModel(UUID id, VehicleModel vehicleModel) {
        Optional<VehicleModel> existingVehicleModelOptional = vehicleModelRepository.findById(id);
        if (existingVehicleModelOptional.isPresent()) {
            VehicleModel existingVehicleModel = existingVehicleModelOptional.get();
            existingVehicleModel.setVehicleModelName(vehicleModel.getVehicleModelName());
            existingVehicleModel.setVehicle(vehicleModel.getVehicle());
            existingVehicleModel.setVehicleCategory(vehicleModel.getVehicleCategory());
            return vehicleModelRepository.save(existingVehicleModel);
        } else {
            return null;
        }
    }

    public void deleteById(String id) {
        vehicleModelRepository.deleteById(UUID.fromString(id));
    }
}