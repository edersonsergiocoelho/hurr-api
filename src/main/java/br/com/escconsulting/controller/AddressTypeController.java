package br.com.escconsulting.controller;

import br.com.escconsulting.dto.address.type.AddressTypeSearchDTO;
import br.com.escconsulting.entity.AddressType;
import br.com.escconsulting.service.AddressTypeService;
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
@RequestMapping("/address-type")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressTypeController {

    private final AddressTypeService addressTypeService;

    @GetMapping("/{addressTypeId}")
    public ResponseEntity<AddressType> findById(@PathVariable("addressTypeId") UUID addressTypeId) {
        return addressTypeService.findById(addressTypeId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<AddressType>> findAll() {
        List<AddressType> listAddressType = addressTypeService.findAll();
        return ResponseEntity.ok(listAddressType);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody AddressTypeSearchDTO addressTypeSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<AddressType> countries = addressTypeService.searchPage(addressTypeSearchDTO, pageable);

        return ResponseEntity.ok(countries);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AddressType addressType) {
        return addressTypeService.save(addressType)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save addressType."));
    }

    @PutMapping("/{addressTypeId}")
    public ResponseEntity<?> update(@PathVariable("addressTypeId") UUID addressTypeId,
                                    @RequestBody AddressType addressType) {
        return addressTypeService.update(addressTypeId, addressType)
                .map(updatedAddressType -> ResponseEntity.ok(updatedAddressType))
                .orElseThrow(() -> new IllegalStateException("Failed to update addressType."));
    }

    @DeleteMapping("/{addressTypeId}")
    public ResponseEntity<?> delete(@PathVariable("addressTypeId") UUID addressTypeId) {
        addressTypeService.delete(addressTypeId);
        return ResponseEntity.noContent().build();
    }
}