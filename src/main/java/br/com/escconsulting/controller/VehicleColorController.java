package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.vehicle.color.VehicleColorDTO;
import br.com.escconsulting.dto.vehicle.color.VehicleColorSearchDTO;
import br.com.escconsulting.entity.VehicleColor;
import br.com.escconsulting.mapper.VehicleColorMapper;
import br.com.escconsulting.service.VehicleColorService;
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
@RequestMapping("/vehicle-color")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VehicleColorController {

    private final VehicleColorService vehicleColorService;

    @GetMapping("/{vehicleColorId}")
    public ResponseEntity<?> findById(@PathVariable("vehicleColorId") UUID vehicleColorId) {
        return vehicleColorService.findById(vehicleColorId)
                .map(VehicleColorMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                vehicleColorService.findAll().stream()
                        .map(VehicleColorMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @CurrentUser LocalUser localUser,
            @RequestBody VehicleColorSearchDTO vehicleColorSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<VehicleColorDTO> vehicleColors = vehicleColorService.searchPage(localUser, vehicleColorSearchDTO, pageable);

        return ResponseEntity.ok(vehicleColors);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody VehicleColor vehicleColor) {
        return vehicleColorService.save(vehicleColor)
                .map(VehicleColorMapper.INSTANCE::toDTO)
                .map(savedVehicleColor -> ResponseEntity.status(HttpStatus.CREATED).build())
                .orElseThrow(() -> new IllegalStateException("Failed to save customer withdrawal request."));
    }

    @PutMapping("/{vehicleColorId}")
    public ResponseEntity<?> update(@PathVariable("vehicleColorId") UUID vehicleColorId,
                                    @RequestBody VehicleColor vehicleColor) {
        return vehicleColorService.update(vehicleColorId, vehicleColor)
                .map(VehicleColorMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer withdrawal request."));
    }

    @DeleteMapping("/{vehicleColorId}")
    public ResponseEntity<?> delete(@PathVariable("vehicleColorId") UUID vehicleColorId) {
        vehicleColorService.delete(vehicleColorId);
        return ResponseEntity.noContent().build();
    }
}