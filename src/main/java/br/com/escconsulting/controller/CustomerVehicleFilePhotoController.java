package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.file.photo.CustomerVehicleFilePhotoDTO;
import br.com.escconsulting.dto.customer.vehicle.file.photo.CustomerVehicleFilePhotoSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleFilePhoto;
import br.com.escconsulting.mapper.CustomerVehicleFilePhotoMapper;
import br.com.escconsulting.service.CustomerVehicleFilePhotoService;
import lombok.RequiredArgsConstructor;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer-vehicle-file-photo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleFilePhotoController {

    private final CustomerVehicleFilePhotoService customerVehicleFilePhotoService;

    @GetMapping("/{customerVehicleFilePhotoId}")
    public ResponseEntity<?> findById(@PathVariable("customerVehicleFilePhotoId") UUID customerVehicleFilePhotoId) {
        return customerVehicleFilePhotoService.findById(customerVehicleFilePhotoId)
                .map(CustomerVehicleFilePhotoMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/by/customer-vehicle/{customerVehicleId}")
    public ResponseEntity<?> findByCustomerVehicle(@PathVariable("customerVehicleId") UUID customerVehicleId) {
        return ResponseEntity.ok(
                customerVehicleFilePhotoService.findByCustomerVehicle(customerVehicleId).stream()
                        .map(CustomerVehicleFilePhotoMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/by/customer-vehicle/{customerVehicleId}/and/cover-photo")
    public ResponseEntity<CustomerVehicleFilePhotoDTO> findByCustomerVehicleAndCoverPhoto(@PathVariable("customerVehicleId") UUID customerVehicleId) {
        return customerVehicleFilePhotoService.findByCustomerVehicleAndCoverPhoto(customerVehicleId)
                .map(CustomerVehicleFilePhotoMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                customerVehicleFilePhotoService.findAll().stream()
                        .map(CustomerVehicleFilePhotoMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(@CurrentUser LocalUser localUser,
                                    @RequestBody CustomerVehicleFilePhotoSearchDTO customerVehicleFilePhotoSearchDTO,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                    @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
                                    @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<CustomerVehicleFilePhotoDTO> customerVehicleFilePhotos = customerVehicleFilePhotoService.searchPage(localUser, customerVehicleFilePhotoSearchDTO, pageable);

        return ResponseEntity.ok(customerVehicleFilePhotos);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerVehicleFilePhoto customerVehicleFilePhoto) {
        return customerVehicleFilePhotoService.save(customerVehicleFilePhoto)
                .map(CustomerVehicleFilePhotoMapper.INSTANCE::toDTO)
                .map(savedCustomerVehicleFilePhoto -> ResponseEntity.status(HttpStatus.CREATED).build())
                .orElseThrow(() -> new IllegalStateException("Failed to save customer withdrawal request."));
    }

    @PostMapping("/all")
    public ResponseEntity<?> saveBulk(@RequestBody List<CustomerVehicleFilePhoto> customerVehicleFilePhotos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                customerVehicleFilePhotoService.saveAll(customerVehicleFilePhotos).stream()
                .map(CustomerVehicleFilePhotoMapper.INSTANCE::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{customerVehicleFilePhotoId}")
    public ResponseEntity<?> update(@PathVariable("customerVehicleFilePhotoId") UUID customerVehicleFilePhotoId,
                                    @RequestBody CustomerVehicleFilePhoto customerVehicleFilePhoto) {
        return customerVehicleFilePhotoService.update(customerVehicleFilePhotoId, customerVehicleFilePhoto)
                .map(CustomerVehicleFilePhotoMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer withdrawal request."));
    }

    @DeleteMapping("/{customerVehicleFilePhotoId}")
    public ResponseEntity<?> delete(@PathVariable("customerVehicleFilePhotoId") UUID customerVehicleFilePhotoId) {
        customerVehicleFilePhotoService.delete(customerVehicleFilePhotoId);
        return ResponseEntity.noContent().build();
    }
}