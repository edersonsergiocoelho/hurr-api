package br.com.escconsulting.controller;

import br.com.escconsulting.dto.customer.vehicle.SearchCustomerVehicle;
import br.com.escconsulting.entity.CustomerVehicle;
import br.com.escconsulting.service.CustomerVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer-vehicle")
public class CustomerVehicleController {

    @Autowired
    private CustomerVehicleService customerVehicleService;

    @GetMapping
    public ResponseEntity<List<CustomerVehicle>> getAllCustomerVehicles() {
        List<CustomerVehicle> customerVehicles = customerVehicleService.findAll();
        return ResponseEntity.ok(customerVehicles);
    }

    @GetMapping("/{customerVehicleId}")
    public ResponseEntity<CustomerVehicle> getCustomerVehicleById(@PathVariable UUID customerVehicleId) {
        CustomerVehicle customerVehicle = customerVehicleService.findById(customerVehicleId);
        return ResponseEntity.ok(customerVehicle);
    }

    @PostMapping("/search")
    public ResponseEntity<List<CustomerVehicle>> searchCustomerVehicles(
            @RequestBody SearchCustomerVehicle searchCustomerVehicle
    ) {
        List<CustomerVehicle> customerVehicles = customerVehicleService.search(searchCustomerVehicle);
        return ResponseEntity.ok(customerVehicles);
    }

    @PostMapping
    public ResponseEntity<CustomerVehicle> createCustomerVehicle(@RequestBody CustomerVehicle customerVehicle) {
        customerVehicleService.save(customerVehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerVehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerVehicle> updateCustomerVehicle(@PathVariable UUID id, @RequestBody CustomerVehicle customerVehicle) {
        customerVehicle.setCustomerVehicleId(id);
        customerVehicleService.save(customerVehicle);
        return ResponseEntity.ok(customerVehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerVehicle(@PathVariable UUID id) {
        customerVehicleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}