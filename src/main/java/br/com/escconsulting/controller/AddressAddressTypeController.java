package br.com.escconsulting.controller;

import br.com.escconsulting.dto.address.address.type.AddressAddressTypeSearchDTO;
import br.com.escconsulting.entity.AddressAddressType;
import br.com.escconsulting.service.AddressAddressTypeService;
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
@RequestMapping("/address-address-type")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressAddressTypeController {

    private final AddressAddressTypeService addressAddressTypeService;

    @GetMapping("/{addressAddressTypeId}")
    public ResponseEntity<AddressAddressType> findById(@PathVariable("addressAddressTypeId") UUID addressAddressTypeId) {
        return addressAddressTypeService.findById(addressAddressTypeId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<AddressAddressType>> findAll() {
        List<AddressAddressType> listAddressAddressType = addressAddressTypeService.findAll();
        return ResponseEntity.ok(listAddressAddressType);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody AddressAddressTypeSearchDTO addressAddressTypeSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<AddressAddressType> countries = addressAddressTypeService.searchPage(addressAddressTypeSearchDTO, pageable);

        return ResponseEntity.ok(countries);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AddressAddressType addressAddressType) {
        return addressAddressTypeService.save(addressAddressType)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save addressAddressType."));
    }

    @PutMapping("/{addressAddressTypeId}")
    public ResponseEntity<?> update(@PathVariable("addressAddressTypeId") UUID addressAddressTypeId,
                                    @RequestBody AddressAddressType addressAddressType) {
        return addressAddressTypeService.update(addressAddressTypeId, addressAddressType)
                .map(updatedAddressAddressType -> ResponseEntity.ok(updatedAddressAddressType))
                .orElseThrow(() -> new IllegalStateException("Failed to update addressAddressType."));
    }

    @DeleteMapping("/{addressAddressTypeId}")
    public ResponseEntity<?> delete(@PathVariable("addressAddressTypeId") UUID addressAddressTypeId) {
        addressAddressTypeService.delete(addressAddressTypeId);
        return ResponseEntity.noContent().build();
    }
}