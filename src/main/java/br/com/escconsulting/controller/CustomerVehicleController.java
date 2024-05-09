package br.com.escconsulting.controller;

import br.com.escconsulting.dto.customer.vehicle.SearchCustomerVehicle;
import br.com.escconsulting.entity.CustomerVehicle;
import br.com.escconsulting.service.CustomerVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer-vehicle")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleController {

    private final CustomerVehicleService customerVehicleService;

    @GetMapping("/{customerVehicleId}")
    public ResponseEntity<CustomerVehicle> findById(@PathVariable UUID customerVehicleId) {
        return customerVehicleService.findById(customerVehicleId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<CustomerVehicle>> findAll() {
        List<CustomerVehicle> customerVehicles = customerVehicleService.findAll();
        return ResponseEntity.ok(customerVehicles);
    }

    @PostMapping("/search")
    public ResponseEntity<List<CustomerVehicle>> searchCustomerVehicles(
            @RequestBody SearchCustomerVehicle searchCustomerVehicle
    ) {
        List<CustomerVehicle> customerVehicles = customerVehicleService.search(searchCustomerVehicle);
        return ResponseEntity.ok(customerVehicles);
    }

    @PostMapping
    public ResponseEntity<CustomerVehicle> save(@RequestBody CustomerVehicle customerVehicle) {
        customerVehicleService.save(customerVehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerVehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerVehicle> update(@PathVariable UUID id, @RequestBody CustomerVehicle customerVehicle) {
        customerVehicle.setCustomerVehicleId(id);
        customerVehicleService.save(customerVehicle);
        return ResponseEntity.ok(customerVehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        customerVehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}