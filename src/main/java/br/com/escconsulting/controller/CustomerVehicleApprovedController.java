package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedDTO;
import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleApproved;
import br.com.escconsulting.mapper.CustomerVehicleApprovedMapper;
import br.com.escconsulting.service.CustomerVehicleApprovedService;
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
@RequestMapping("/customer-vehicle-approved")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleApprovedController {

    private final CustomerVehicleApprovedService customerVehicleApprovedService;

    @GetMapping("/{customerVehicleApprovedId}")
    public ResponseEntity<?> findById(@PathVariable("customerVehicleApprovedId") UUID customerVehicleApprovedId) {
        return customerVehicleApprovedService.findById(customerVehicleApprovedId)
                .map(CustomerVehicleApprovedMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                customerVehicleApprovedService.findAll().stream()
                        .map(CustomerVehicleApprovedMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(@CurrentUser LocalUser localUser,
                                    @RequestBody CustomerVehicleApprovedSearchDTO customerVehicleApprovedSearchDTO,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                    @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
                                    @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<CustomerVehicleApprovedDTO> customerVehicleApproveds = customerVehicleApprovedService.searchPage(localUser, customerVehicleApprovedSearchDTO, pageable);

        return ResponseEntity.ok(customerVehicleApproveds);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerVehicleApproved customerVehicleApproved) {
        return customerVehicleApprovedService.save(customerVehicleApproved)
                .map(CustomerVehicleApprovedMapper.INSTANCE::toDTO)
                .map(savedCustomerVehicleApproved -> ResponseEntity.status(HttpStatus.CREATED).build())
                .orElseThrow(() -> new IllegalStateException("Failed to save customer withdrawal request."));
    }

    @PostMapping("/all")
    public ResponseEntity<?> saveBulk(@RequestBody List<CustomerVehicleApproved> customerVehicleApproveds) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                customerVehicleApprovedService.saveAll(customerVehicleApproveds).stream()
                .map(CustomerVehicleApprovedMapper.INSTANCE::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{customerVehicleApprovedId}")
    public ResponseEntity<?> update(@PathVariable("customerVehicleApprovedId") UUID customerVehicleApprovedId,
                                    @RequestBody CustomerVehicleApproved customerVehicleApproved) {
        return customerVehicleApprovedService.update(customerVehicleApprovedId, customerVehicleApproved)
                .map(CustomerVehicleApprovedMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer withdrawal request."));
    }

    @DeleteMapping("/{customerVehicleApprovedId}")
    public ResponseEntity<?> delete(@PathVariable("customerVehicleApprovedId") UUID customerVehicleApprovedId) {
        customerVehicleApprovedService.delete(customerVehicleApprovedId);
        return ResponseEntity.noContent().build();
    }
}