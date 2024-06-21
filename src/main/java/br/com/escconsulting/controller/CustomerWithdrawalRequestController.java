package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestDTO;
import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestSearchDTO;
import br.com.escconsulting.entity.CustomerWithdrawalRequest;
import br.com.escconsulting.mapper.CustomerWithdrawalRequestMapper;
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
                .map(CustomerWithdrawalRequestMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                customerWithdrawalRequestService.findAll().stream()
                        .map(CustomerWithdrawalRequestMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @CurrentUser LocalUser localUser,
            @RequestBody CustomerWithdrawalRequestSearchDTO customerWithdrawalRequestSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<CustomerWithdrawalRequestDTO> customerWithdrawalRequests = customerWithdrawalRequestService.searchPage(localUser, customerWithdrawalRequestSearchDTO, pageable);

        return ResponseEntity.ok(customerWithdrawalRequests);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerWithdrawalRequest customerWithdrawalRequest) {
        return customerWithdrawalRequestService.save(customerWithdrawalRequest)
                .map(CustomerWithdrawalRequestMapper.INSTANCE::toDTO)
                .map(savedCustomerWithdrawalRequest -> ResponseEntity.status(HttpStatus.CREATED).build())
                .orElseThrow(() -> new IllegalStateException("Failed to save customer withdrawal request."));
    }

    @PostMapping("/all")
    public ResponseEntity<?> saveBulk(@RequestBody List<CustomerWithdrawalRequest> customerWithdrawalRequests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                customerWithdrawalRequestService.saveAll(customerWithdrawalRequests).stream()
                .map(CustomerWithdrawalRequestMapper.INSTANCE::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{customerWithdrawalRequestId}")
    public ResponseEntity<?> update(@PathVariable("customerWithdrawalRequestId") UUID customerWithdrawalRequestId,
                                    @RequestBody CustomerWithdrawalRequest customerWithdrawalRequest) {
        return customerWithdrawalRequestService.update(customerWithdrawalRequestId, customerWithdrawalRequest)
                .map(CustomerWithdrawalRequestMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer withdrawal request."));
    }

    @PutMapping("/approval/{customerWithdrawalRequestId}")
    public ResponseEntity<?> approval(@PathVariable("customerWithdrawalRequestId") UUID customerWithdrawalRequestId) {
        return customerWithdrawalRequestService.approval(customerWithdrawalRequestId)
                .map(CustomerWithdrawalRequestMapper.INSTANCE::toDTONoFile)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer withdrawal request."));
    }

    @DeleteMapping("/{customerWithdrawalRequestId}")
    public ResponseEntity<?> delete(@PathVariable("customerWithdrawalRequestId") UUID customerWithdrawalRequestId) {
        customerWithdrawalRequestService.delete(customerWithdrawalRequestId);
        return ResponseEntity.noContent().build();
    }
}