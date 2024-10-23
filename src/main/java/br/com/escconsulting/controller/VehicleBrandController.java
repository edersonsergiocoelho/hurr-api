package br.com.escconsulting.controller;

import br.com.escconsulting.dto.vehicle.brand.VehicleBrandDTO;
import br.com.escconsulting.dto.vehicle.brand.VehicleBrandSearchDTO;
import br.com.escconsulting.entity.VehicleBrand;
import br.com.escconsulting.mapper.VehicleBrandMapper;
import br.com.escconsulting.service.VehicleBrandService;
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
@RequestMapping("/vehicle-brand")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VehicleBrandController {

    private final VehicleBrandService vehicleBrandService;

    @GetMapping("/{vehicleBrandId}")
    public ResponseEntity<VehicleBrandDTO> findById(@PathVariable("vehicleBrandId") UUID vehicleBrandId) {
        return vehicleBrandService.findById(vehicleBrandId)
                .map(VehicleBrandMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }

    @GetMapping
    public ResponseEntity<List<VehicleBrandDTO>> findAll() {
        return ResponseEntity.ok(
                vehicleBrandService.findAll().stream()
                        .map(VehicleBrandMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody VehicleBrandSearchDTO vehicleBrandSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<VehicleBrandDTO> vehicleBrands = vehicleBrandService.searchPage(vehicleBrandSearchDTO, pageable);

        return ResponseEntity.ok(vehicleBrands);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody VehicleBrand vehicleBrand) {
        return vehicleBrandService.save(vehicleBrand)
                .map(VehicleBrandMapper.INSTANCE::toDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseThrow(() -> new IllegalStateException("Failed to save payment method."));
    }

    @PutMapping("/{vehicleBrandId}")
    public ResponseEntity<?> update(@PathVariable("vehicleBrandId") UUID vehicleBrandId,
                                    @RequestBody VehicleBrand vehicleBrand) {
        return vehicleBrandService.update(vehicleBrandId, vehicleBrand)
                .map(VehicleBrandMapper.INSTANCE::toDTO)
                .map(updatedVehicleBrand -> ResponseEntity.ok(updatedVehicleBrand))
                .orElseThrow(() -> new IllegalStateException("Failed to update payment method."));
    }

    @DeleteMapping("/{vehicleBrandId}")
    public ResponseEntity<?> delete(@PathVariable("vehicleBrandId") UUID vehicleBrandId) {
        vehicleBrandService.delete(vehicleBrandId);
        return ResponseEntity.noContent().build();
    }
}