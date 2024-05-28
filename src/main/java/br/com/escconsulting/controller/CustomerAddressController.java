package br.com.escconsulting.controller;

import br.com.escconsulting.dto.customer.address.CustomerAddressSaveAddressDTO;
import br.com.escconsulting.dto.customer.address.CustomerAddressSearchDTO;
import br.com.escconsulting.entity.CustomerAddress;
import br.com.escconsulting.service.CustomerAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer-address")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerAddressController {

    private final CustomerAddressService customerAddressService;

    @GetMapping("/{customerAddressId}")
    public ResponseEntity<CustomerAddress> findById(@PathVariable("customerAddressId") UUID customerAddressId) {
        return customerAddressService.findById(customerAddressId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/by/customerId/{customerId}")
    public ResponseEntity<List<CustomerAddress>> findByCustomerId(@PathVariable("customerId") UUID customerId) {
        List<CustomerAddress> listCustomerAddress = customerAddressService.findByCustomerId(customerId);
        return ResponseEntity.ok(listCustomerAddress);
    }

    @GetMapping("/by/customerId/{customerId}/and/addressTypeName/{addressTypeName}")
    public ResponseEntity<List<CustomerAddress>> findByCustomerIdAndAddressTypeName(@PathVariable("customerId") UUID customerId,
                                                                                    @PathVariable("addressTypeName") String addressTypeName) {
        List<CustomerAddress> listCustomerAddress = customerAddressService.findByCustomerIdAndAddressTypeName(customerId, addressTypeName);
        return ResponseEntity.ok(listCustomerAddress);
    }

    @GetMapping
    public ResponseEntity<List<CustomerAddress>> findAll() {
        List<CustomerAddress> listCustomerAddress = customerAddressService.findAll();
        return ResponseEntity.ok(listCustomerAddress);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody CustomerAddressSearchDTO customerAddressSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<CustomerAddress> customerAddresss = customerAddressService.searchPage(customerAddressSearchDTO, pageable);

        return ResponseEntity.ok(customerAddresss);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerAddress customerAddress) {
        return customerAddressService.save(customerAddress)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save customer address."));
    }

    @PostMapping("/address")
    public ResponseEntity<?> saveAddress(@RequestBody CustomerAddressSaveAddressDTO customerAddressSaveAddressDTO) {
        return customerAddressService.saveAddress(customerAddressSaveAddressDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save customer address save address."));
    }

    @PutMapping("/{customerAddressId}")
    public ResponseEntity<?> update(@PathVariable("customerAddressId") UUID customerAddressId,
                                    @RequestBody CustomerAddress customerAddress) {
        return customerAddressService.update(customerAddressId, customerAddress)
                .map(updatedCustomerAddress -> ResponseEntity.ok(updatedCustomerAddress))
                .orElseThrow(() -> new IllegalStateException("Failed to update customer address."));
    }

    @DeleteMapping("/{customerAddressId}")
    public ResponseEntity<?> delete(@PathVariable("customerAddressId") UUID customerAddressId) {
        customerAddressService.delete(customerAddressId);
        return ResponseEntity.noContent().build();
    }
}