package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.vehicle.transmission.VehicleTransmissionDTO;
import br.com.escconsulting.dto.vehicle.transmission.VehicleTransmissionSearchDTO;
import br.com.escconsulting.entity.VehicleTransmission;
import br.com.escconsulting.mapper.VehicleTransmissionMapper;
import br.com.escconsulting.service.VehicleTransmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehicle-transmission")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VehicleTransmissionController {

    private final VehicleTransmissionService vehicleTransmissionService;

    @GetMapping("/{vehicleTransmissionId}")
    public ResponseEntity<?> findById(@PathVariable("vehicleTransmissionId") UUID vehicleTransmissionId) {
        return vehicleTransmissionService.findById(vehicleTransmissionId)
                .map(VehicleTransmissionMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                vehicleTransmissionService.findAll().stream()
                        .map(VehicleTransmissionMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @CurrentUser LocalUser localUser,
            @RequestBody VehicleTransmissionSearchDTO vehicleTransmissionSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<VehicleTransmissionDTO> vehicleTransmissions = vehicleTransmissionService.searchPage(localUser, vehicleTransmissionSearchDTO, pageable);

        return ResponseEntity.ok(vehicleTransmissions);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody VehicleTransmission vehicleTransmission) {
        return vehicleTransmissionService.save(vehicleTransmission)
                .map(VehicleTransmissionMapper.INSTANCE::toDTO)
                .map(savedVehicleTransmission -> ResponseEntity.status(HttpStatus.CREATED).build())
                .orElseThrow(() -> new IllegalStateException("Failed to save customer withdrawal request."));
    }

    @PutMapping("/{vehicleTransmissionId}")
    public ResponseEntity<?> update(@PathVariable("vehicleTransmissionId") UUID vehicleTransmissionId,
                                    @RequestBody VehicleTransmission vehicleTransmission) {
        return vehicleTransmissionService.update(vehicleTransmissionId, vehicleTransmission)
                .map(VehicleTransmissionMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer withdrawal request."));
    }

    @DeleteMapping("/{vehicleTransmissionId}")
    public ResponseEntity<?> delete(@PathVariable("vehicleTransmissionId") UUID vehicleTransmissionId) {
        vehicleTransmissionService.delete(vehicleTransmissionId);
        return ResponseEntity.noContent().build();
    }
}