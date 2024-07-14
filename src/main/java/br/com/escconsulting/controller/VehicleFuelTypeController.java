package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.vehicle.fuel.type.VehicleFuelTypeDTO;
import br.com.escconsulting.dto.vehicle.fuel.type.VehicleFuelTypeSearchDTO;
import br.com.escconsulting.entity.VehicleFuelType;
import br.com.escconsulting.mapper.VehicleFuelTypeMapper;
import br.com.escconsulting.service.VehicleFuelTypeService;
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
@RequestMapping("/vehicle-fuel-type")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VehicleFuelTypeController {

    private final VehicleFuelTypeService vehicleFuelTypeService;

    @GetMapping("/{vehicleFuelTypeId}")
    public ResponseEntity<?> findById(@PathVariable("vehicleFuelTypeId") UUID vehicleFuelTypeId) {
        return vehicleFuelTypeService.findById(vehicleFuelTypeId)
                .map(VehicleFuelTypeMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                vehicleFuelTypeService.findAll().stream()
                        .map(VehicleFuelTypeMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @CurrentUser LocalUser localUser,
            @RequestBody VehicleFuelTypeSearchDTO vehicleFuelTypeSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<VehicleFuelTypeDTO> vehicleFuelTypes = vehicleFuelTypeService.searchPage(localUser, vehicleFuelTypeSearchDTO, pageable);

        return ResponseEntity.ok(vehicleFuelTypes);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody VehicleFuelType vehicleFuelType) {
        return vehicleFuelTypeService.save(vehicleFuelType)
                .map(VehicleFuelTypeMapper.INSTANCE::toDTO)
                .map(savedVehicleFuelType -> ResponseEntity.status(HttpStatus.CREATED).build())
                .orElseThrow(() -> new IllegalStateException("Failed to save customer withdrawal request."));
    }

    @PutMapping("/{vehicleFuelTypeId}")
    public ResponseEntity<?> update(@PathVariable("vehicleFuelTypeId") UUID vehicleFuelTypeId,
                                    @RequestBody VehicleFuelType vehicleFuelType) {
        return vehicleFuelTypeService.update(vehicleFuelTypeId, vehicleFuelType)
                .map(VehicleFuelTypeMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer withdrawal request."));
    }

    @DeleteMapping("/{vehicleFuelTypeId}")
    public ResponseEntity<?> delete(@PathVariable("vehicleFuelTypeId") UUID vehicleFuelTypeId) {
        vehicleFuelTypeService.delete(vehicleFuelTypeId);
        return ResponseEntity.noContent().build();
    }
}