package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleDTO;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleSaveDTO;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleSearchDTO;
import br.com.escconsulting.entity.CustomerVehicle;
import br.com.escconsulting.mapper.CustomerVehicleMapper;
import br.com.escconsulting.service.CustomerVehicleService;
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
@RequestMapping("/customer-vehicle")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleController {

    private final CustomerVehicleService customerVehicleService;

    @GetMapping("/{customerVehicleId}")
    public ResponseEntity<?> findById(@PathVariable("customerVehicleId") UUID customerVehicleId) {
        return customerVehicleService.findById(customerVehicleId)
                .map(CustomerVehicleMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                customerVehicleService.findAll().stream()
                        .map(CustomerVehicleMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search")
    public ResponseEntity<List<CustomerVehicle>> searchCustomerVehicles(
            @RequestBody CustomerVehicleSearchDTO customerVehicleSearchDTO
    ) {
        List<CustomerVehicle> customerVehicles = customerVehicleService.search(customerVehicleSearchDTO);
        return ResponseEntity.ok(customerVehicles);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(@CurrentUser LocalUser localUser,
                                    @RequestBody CustomerVehicleSearchDTO customerVehicleSearchDTO,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                    @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
                                    @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<CustomerVehicleDTO> customerVehicles = customerVehicleService.searchPage(localUser, customerVehicleSearchDTO, pageable);

        return ResponseEntity.ok(customerVehicles);
    }

    @PostMapping
    public ResponseEntity<?> save(@CurrentUser LocalUser localUser,
                                  @RequestBody CustomerVehicleSaveDTO customerVehicleSaveDTO) {
        return customerVehicleService.save(localUser, customerVehicleSaveDTO)
                .map(CustomerVehicleMapper.INSTANCE::toDTO)
                .map(savedCustomerVehicle -> ResponseEntity.status(HttpStatus.CREATED).build())
                .orElseThrow(() -> new IllegalStateException("Failed to save customer vehicle."));
    }

    @PutMapping("/{customerVehicleId}")
    public ResponseEntity<?> update(@PathVariable("customerVehicleId") UUID customerVehicleId,
                                    @RequestBody CustomerVehicle customerVehicle) {
        return customerVehicleService.update(customerVehicleId, customerVehicle)
                .map(CustomerVehicleMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer vehicle."));
    }

    @DeleteMapping("/{customerVehicleId}")
    public ResponseEntity<?> delete(@PathVariable("customerVehicleId") UUID customerVehicleId) {
        customerVehicleService.delete(customerVehicleId);
        return ResponseEntity.noContent().build();
    }
}