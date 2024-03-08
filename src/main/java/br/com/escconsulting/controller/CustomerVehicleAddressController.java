package br.com.escconsulting.controller;

import br.com.escconsulting.entity.CustomerVehicleAddress;
import br.com.escconsulting.entity.enumeration.AddressType;
import br.com.escconsulting.service.CustomerVehicleAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer-vehicle-address")
public class CustomerVehicleAddressController {

    private final CustomerVehicleAddressService customerVehicleAddressService;

    @Autowired
    public CustomerVehicleAddressController(CustomerVehicleAddressService customerVehicleAddressService) {
        this.customerVehicleAddressService = customerVehicleAddressService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerVehicleAddress> findById(@PathVariable("id") UUID id) {
        CustomerVehicleAddress customerVehicleAddress = customerVehicleAddressService.findById(id);
        return ResponseEntity.ok(customerVehicleAddress);
    }

    @GetMapping
    public ResponseEntity<List<CustomerVehicleAddress>> findAll() {
        List<CustomerVehicleAddress> listCustomerVehicleAddress = customerVehicleAddressService.findAll();
        return ResponseEntity.ok(listCustomerVehicleAddress);
    }

    @GetMapping("/by/customer-vehicle/{customerVehicleId}")
    public ResponseEntity<List<CustomerVehicleAddress>> findAllByCustomerVehicleId(@PathVariable("customerVehicleId") UUID customerVehicleId) {
        List<CustomerVehicleAddress> listCustomerVehicleAddress = customerVehicleAddressService.findAllByCustomerVehicleId(customerVehicleId);
        return ResponseEntity.ok(listCustomerVehicleAddress);
    }

    @GetMapping("/by/customer-vehicle/{customerVehicleId}/address-type/{addressType}")
    public ResponseEntity<List<CustomerVehicleAddress>> findAllByCustomerVehicleIdAndAddressType(@PathVariable("customerVehicleId") UUID customerVehicleId, @PathVariable("addressType") AddressType addressType) {
        List<CustomerVehicleAddress> listCustomerVehicleAddress = customerVehicleAddressService.findAllByCustomerVehicleIdAndAddressType(customerVehicleId, addressType);
        return ResponseEntity.ok(listCustomerVehicleAddress);
    }

    @PostMapping
    public ResponseEntity<CustomerVehicleAddress> save(@RequestBody CustomerVehicleAddress customerVehicleAddress) {
        CustomerVehicleAddress savedCustomerVehicleAddress = customerVehicleAddressService.save(customerVehicleAddress);
        return new ResponseEntity<>(savedCustomerVehicleAddress, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerVehicleAddress> update(@PathVariable("id") UUID id, @RequestBody CustomerVehicleAddress customerVehicleAddress) {
        CustomerVehicleAddress updateCustomerVehicleAddress = customerVehicleAddressService.update(id, customerVehicleAddress);
        return ResponseEntity.ok(updateCustomerVehicleAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        customerVehicleAddressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}