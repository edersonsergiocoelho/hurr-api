package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.bank.account.CustomerVehicleBankAccountDTO;
import br.com.escconsulting.dto.customer.vehicle.bank.account.CustomerVehicleBankAccountSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleBankAccount;
import br.com.escconsulting.entity.PaymentStatus;
import br.com.escconsulting.mapper.CustomerVehicleBankAccountMapper;
import br.com.escconsulting.mapper.PaymentStatusMapper;
import br.com.escconsulting.service.CustomerBankAccountService;
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
@RequestMapping("/customer-vehicle-bank-account")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleBankAccountController {

    private final CustomerBankAccountService customerBankAccountService;

    @GetMapping("/{customerBankAccountId}")
    public ResponseEntity<CustomerVehicleBankAccountDTO> findById(@PathVariable("customerBankAccountId") UUID customerBankAccountId) {
        return customerBankAccountService.findById(customerBankAccountId)
                .map(CustomerVehicleBankAccountMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<CustomerVehicleBankAccountDTO>> findAll() {
        return ResponseEntity.ok(
                customerBankAccountService.findAll().stream()
                        .map(CustomerVehicleBankAccountMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @CurrentUser LocalUser localUser,
            @RequestBody CustomerVehicleBankAccountSearchDTO customerVehicleBankAccountSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<CustomerVehicleBankAccountDTO> customerBankAccounts = customerBankAccountService.searchPage(localUser, customerVehicleBankAccountSearchDTO, pageable);

        return ResponseEntity.ok(customerBankAccounts);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerVehicleBankAccount customerVehicleBankAccount) {
        return customerBankAccountService.save(customerVehicleBankAccount)
                .map(CustomerVehicleBankAccountMapper.INSTANCE::toDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseThrow(() -> new IllegalStateException("Failed to save customer vehicle bank account."));
    }

    @PutMapping("/{customerBankAccountId}")
    public ResponseEntity<?> update(@PathVariable("customerBankAccountId") UUID customerBankAccountId,
                                    @RequestBody CustomerVehicleBankAccount customerVehicleBankAccount) {
        return customerBankAccountService.update(customerBankAccountId, customerVehicleBankAccount)
                .map(CustomerVehicleBankAccountMapper.INSTANCE::toDTO)
                .map(updatedCustomerBankAccount -> ResponseEntity.ok(updatedCustomerBankAccount))
                .orElseThrow(() -> new IllegalStateException("Failed to update customer bank account."));
    }

    @DeleteMapping("/{customerBankAccountId}")
    public ResponseEntity<?> delete(@PathVariable("customerBankAccountId") UUID customerBankAccountId) {
        customerBankAccountService.delete(customerBankAccountId);
        return ResponseEntity.noContent().build();
    }
}