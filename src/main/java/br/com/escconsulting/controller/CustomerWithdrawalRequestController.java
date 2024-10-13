package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestDTO;
import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleWithdrawalRequest;
import br.com.escconsulting.mapper.CustomerVehicleWithdrawalRequestMapper;
import br.com.escconsulting.service.CustomerWithdrawalRequestService;
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
@RequestMapping("/customer-withdrawal-request")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerWithdrawalRequestController {

    private final CustomerWithdrawalRequestService customerWithdrawalRequestService;

    @GetMapping("/{customerWithdrawalRequestId}")
    public ResponseEntity<?> findById(@PathVariable("customerWithdrawalRequestId") UUID customerWithdrawalRequestId) {
        return customerWithdrawalRequestService.findById(customerWithdrawalRequestId)
                .map(CustomerVehicleWithdrawalRequestMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                customerWithdrawalRequestService.findAll().stream()
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

        Page<CustomerVehicleWithdrawalRequestDTO> customerWithdrawalRequests = customerWithdrawalRequestService.searchPage(localUser, customerVehicleWithdrawalRequestSearchDTO, pageable);

        return ResponseEntity.ok(customerWithdrawalRequests);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest) {
        return customerWithdrawalRequestService.save(customerVehicleWithdrawalRequest)
                .map(CustomerVehicleWithdrawalRequestMapper.INSTANCE::toDTO)
                .map(savedCustomerWithdrawalRequest -> ResponseEntity.status(HttpStatus.CREATED).build())
                .orElseThrow(() -> new IllegalStateException("Failed to save customer withdrawal request."));
    }

    @PostMapping("/all")
    public ResponseEntity<?> saveBulk(@RequestBody List<CustomerVehicleWithdrawalRequest> customerVehicleWithdrawalRequests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                customerWithdrawalRequestService.saveAll(customerVehicleWithdrawalRequests).stream()
                .map(CustomerVehicleWithdrawalRequestMapper.INSTANCE::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{customerWithdrawalRequestId}")
    public ResponseEntity<?> update(@PathVariable("customerWithdrawalRequestId") UUID customerWithdrawalRequestId,
                                    @RequestBody CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest) {
        return customerWithdrawalRequestService.update(customerWithdrawalRequestId, customerVehicleWithdrawalRequest)
                .map(CustomerVehicleWithdrawalRequestMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer withdrawal request."));
    }

    @PutMapping("/approval/{customerWithdrawalRequestId}")
    public ResponseEntity<?> approval(@PathVariable("customerWithdrawalRequestId") UUID customerWithdrawalRequestId) {
        return customerWithdrawalRequestService.approval(customerWithdrawalRequestId)
                .map(CustomerVehicleWithdrawalRequestMapper.INSTANCE::toDTONoFile)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer withdrawal request."));
    }

    @DeleteMapping("/{customerWithdrawalRequestId}")
    public ResponseEntity<?> delete(@PathVariable("customerWithdrawalRequestId") UUID customerWithdrawalRequestId) {
        customerWithdrawalRequestService.delete(customerWithdrawalRequestId);
        return ResponseEntity.noContent().build();
    }
}