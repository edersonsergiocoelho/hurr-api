package br.com.escconsulting.controller;

import br.com.escconsulting.entity.Vehicle;
import br.com.escconsulting.mapper.VehicleMapper;
import br.com.escconsulting.service.VehicleService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable UUID id) {
        Optional<Vehicle> vehicleOptional = vehicleService.findById(id);
        if (vehicleOptional.isPresent()) {
            return ResponseEntity.ok(vehicleOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {
        List<Vehicle> vehicles = vehicleService.findAll();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/vehicle-brand/{vehicleBrandId}")
    @PermitAll
    public ResponseEntity<?> getVehiclesByBrandId(@PathVariable UUID vehicleBrandId) {
        return ResponseEntity.ok(
                vehicleService.findByVehicleBrandVehicleBrandId(vehicleBrandId).stream()
                        .map(VehicleMapper.INSTANCE::toDTONoFile)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<Vehicle> save(@RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.save(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable UUID id, @RequestBody Vehicle vehicle) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicle);
        if (updatedVehicle != null) {
            return ResponseEntity.ok(updatedVehicle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}