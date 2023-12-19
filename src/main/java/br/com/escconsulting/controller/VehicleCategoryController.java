package br.com.escconsulting.controller;

import br.com.escconsulting.entity.VehicleCategory;
import br.com.escconsulting.service.VehicleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vehicle-category")
public class VehicleCategoryController {

    private final VehicleCategoryService vehicleCategoryService;

    @Autowired
    public VehicleCategoryController(VehicleCategoryService vehicleCategoryService) {
        this.vehicleCategoryService = vehicleCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<VehicleCategory>> getAllVehicleCategories() {
        List<VehicleCategory> vehicleCategories = vehicleCategoryService.getAllVehicleCategories();
        return new ResponseEntity<>(vehicleCategories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleCategory> getVehicleCategoryById(@PathVariable UUID id) {
        VehicleCategory vehicleCategory = vehicleCategoryService.getVehicleCategoryById(id);
        return vehicleCategory != null ? ResponseEntity.ok(vehicleCategory) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<VehicleCategory> createVehicleCategory(@RequestBody VehicleCategory vehicleCategory) {
        VehicleCategory createdCategory = vehicleCategoryService.createVehicleCategory(vehicleCategory);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleCategory> updateVehicleCategory(@PathVariable UUID id, @RequestBody VehicleCategory vehicleCategory) {
        VehicleCategory updatedCategory = vehicleCategoryService.updateVehicleCategory(id, vehicleCategory);
        return updatedCategory != null ? ResponseEntity.ok(updatedCategory) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleCategory(@PathVariable UUID id) {
        vehicleCategoryService.deleteVehicleCategory(id);
        return ResponseEntity.noContent().build();
    }
}