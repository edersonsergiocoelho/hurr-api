package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.address.CustomerAddressSaveAddressDTO;
import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressDTO;
import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressSaveAddressDTO;
import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressSearchDTO;
import br.com.escconsulting.dto.customer.vehicle.file.photo.CustomerVehicleFilePhotoDTO;
import br.com.escconsulting.entity.CustomerVehicleAddress;
import br.com.escconsulting.service.CustomerVehicleAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/by/customer-vehicle/{customerVehicleId}/address-type/{addressTypeName}")
    public ResponseEntity<List<CustomerVehicleAddress>> findAllByCustomerVehicleIdAndAddressType(@PathVariable("customerVehicleId") UUID customerVehicleId, @PathVariable("addressTypeName") String addressTypeName) {
        List<CustomerVehicleAddress> listCustomerVehicleAddress = customerVehicleAddressService.findAllByCustomerVehicleIdAndAddressType(customerVehicleId, addressTypeName);
        return ResponseEntity.ok(listCustomerVehicleAddress);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(@RequestBody CustomerVehicleAddressSearchDTO customerVehicleAddressSearchDTO,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                    @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
                                    @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<CustomerVehicleAddressDTO> customerVehicleAddresses = customerVehicleAddressService.searchPage(customerVehicleAddressSearchDTO, pageable);

        return ResponseEntity.ok(customerVehicleAddresses);
    }

    @PostMapping
    public ResponseEntity<CustomerVehicleAddress> save(@RequestBody CustomerVehicleAddress customerVehicleAddress) {
        return customerVehicleAddressService.save(customerVehicleAddress)
                .map(savedAddress -> ResponseEntity.status(HttpStatus.CREATED).body(savedAddress))
                .orElseThrow(() -> new IllegalStateException("Failed to save customer vehicle address save address."));
    }

    @PostMapping("/address")
    public ResponseEntity<CustomerVehicleAddress> saveAddress(@RequestBody CustomerVehicleAddressSaveAddressDTO customerVehicleAddressSaveAddressDTO) {
        return customerVehicleAddressService.saveAddress(customerVehicleAddressSaveAddressDTO)
                .map(savedAddress -> ResponseEntity.status(HttpStatus.CREATED).body(savedAddress))
                .orElseThrow(() -> new IllegalStateException("Failed to save customer vehicle address save address."));
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