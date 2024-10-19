package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestDTO;
import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleWithdrawalRequest;
import br.com.escconsulting.mapper.CustomerVehicleWithdrawalRequestMapper;
import br.com.escconsulting.service.CustomerVehicleWithdrawalRequestService;
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
@RequestMapping("/customer-vehicle-withdrawal-request")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleWithdrawalRequestController {

    private final CustomerVehicleWithdrawalRequestService customerVehicleWithdrawalRequestService;

    @GetMapping("/{customerVehicleWithdrawalRequestId}")
    public ResponseEntity<?> findById(@PathVariable("customerVehicleWithdrawalRequestId") UUID customerVehicleWithdrawalRequestId) {
        return customerVehicleWithdrawalRequestService.findById(customerVehicleWithdrawalRequestId)
                .map(CustomerVehicleWithdrawalRequestMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                customerVehicleWithdrawalRequestService.findAll().stream()
                        .map(CustomerVehicleWithdrawalRequestMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @CurrentUser LocalUser localUser,
            @RequestBody CustomerVehicleWithdrawalRequestSearchDTO customerVehicleWithdrawalRequestSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<CustomerVehicleWithdrawalRequestDTO> customerVehicleWithdrawalRequests = customerVehicleWithdrawalRequestService.searchPage(localUser, customerVehicleWithdrawalRequestSearchDTO, pageable);

        return ResponseEntity.ok(customerVehicleWithdrawalRequests);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest) {
        return customerVehicleWithdrawalRequestService.save(customerVehicleWithdrawalRequest)
                .map(CustomerVehicleWithdrawalRequestMapper.INSTANCE::toDTO)
                .map(savedCustomerVehicleWithdrawalRequest -> ResponseEntity.status(HttpStatus.CREATED).build())
                .orElseThrow(() -> new IllegalStateException("Failed to save customer withdrawal request."));
    }

    @PostMapping("/all")
    public ResponseEntity<?> saveBulk(@RequestBody List<CustomerVehicleWithdrawalRequest> customerVehicleWithdrawalRequests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                customerVehicleWithdrawalRequestService.saveAll(customerVehicleWithdrawalRequests).stream()
                .map(CustomerVehicleWithdrawalRequestMapper.INSTANCE::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{customerVehicleWithdrawalRequestId}")
    public ResponseEntity<?> update(@PathVariable("customerVehicleWithdrawalRequestId") UUID customerVehicleWithdrawalRequestId,
                                    @RequestBody CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest) {
        return customerVehicleWithdrawalRequestService.update(customerVehicleWithdrawalRequestId, customerVehicleWithdrawalRequest)
                .map(CustomerVehicleWithdrawalRequestMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer withdrawal request."));
    }

    @PutMapping("/approval/{customerVehicleWithdrawalRequestId}")
    public ResponseEntity<?> approval(@PathVariable("customerVehicleWithdrawalRequestId") UUID customerVehicleWithdrawalRequestId) {
        return customerVehicleWithdrawalRequestService.approval(customerVehicleWithdrawalRequestId)
                .map(CustomerVehicleWithdrawalRequestMapper.INSTANCE::toDTONoFile)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer withdrawal request."));
    }

    @DeleteMapping("/{customerVehicleWithdrawalRequestId}")
    public ResponseEntity<?> delete(@PathVariable("customerVehicleWithdrawalRequestId") UUID customerVehicleWithdrawalRequestId) {
        customerVehicleWithdrawalRequestService.delete(customerVehicleWithdrawalRequestId);
        return ResponseEntity.noContent().build();
    }
}