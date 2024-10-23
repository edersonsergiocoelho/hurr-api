package br.com.escconsulting.controller;

import br.com.escconsulting.entity.VehicleModel;
import br.com.escconsulting.mapper.VehicleModelMapper;
import br.com.escconsulting.service.VehicleModelService;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicle-model")
public class VehicleModelController {

    @Autowired
    private VehicleModelService vehicleModelService;

    @GetMapping
    public ResponseEntity<List<VehicleModel>> getVehicleModels() {
        List<VehicleModel> vehicleModels = vehicleModelService.findAll();
        return ResponseEntity.ok(vehicleModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleModel> getVehicleModelById(@PathVariable UUID id) {
        Optional<VehicleModel> vehicleModelOptional = vehicleModelService.findById(id);
        if (vehicleModelOptional.isPresent()) {
            return ResponseEntity.ok(vehicleModelOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/vehicle/{vehicleId}")
    @PermitAll
    public ResponseEntity<?> getVehicleModelByVehicleId(@PathVariable UUID vehicleId) {
        return ResponseEntity.ok(
                vehicleModelService.findByVehicleVehicleId(vehicleId).stream()
                        .map(VehicleModelMapper.INSTANCE::toDTONoFile)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<VehicleModel> createVehicleModel(@RequestBody VehicleModel vehicleModel) {
        VehicleModel createdVehicleModel = vehicleModelService.create(vehicleModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicleModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleModel> updateVehicleModel(@PathVariable UUID id, @RequestBody VehicleModel vehicleModel) {
        VehicleModel updatedModel = vehicleModelService.updateVehicleModel(id, vehicleModel);
        if (updatedModel != null) {
            return ResponseEntity.ok(updatedModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleModel(@PathVariable String id) {
        vehicleModelService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}