package br.com.escconsulting.controller;

import br.com.escconsulting.dto.address.AddressSearchDTO;
import br.com.escconsulting.entity.Address;
import br.com.escconsulting.service.AddressService;
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
@RequestMapping("/address")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/{addressId}")
    public ResponseEntity<Address> findById(@PathVariable("addressId") UUID addressId) {
        return addressService.findById(addressId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<Address>> findAll() {
        List<Address> listAddress = addressService.findAll();
        return ResponseEntity.ok(listAddress);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody AddressSearchDTO addressSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<Address> countries = addressService.searchPage(addressSearchDTO, pageable);

        return ResponseEntity.ok(countries);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Address address) {
        return addressService.save(address)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save address."));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<?> update(@PathVariable("addressId") UUID addressId,
                                    @RequestBody Address address) {
        return addressService.update(addressId, address)
                .map(updatedAddress -> ResponseEntity.ok(updatedAddress))
                .orElseThrow(() -> new IllegalStateException("Failed to update address."));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> delete(@PathVariable("addressId") UUID addressId) {
        addressService.delete(addressId);
        return ResponseEntity.noContent().build();
    }
}