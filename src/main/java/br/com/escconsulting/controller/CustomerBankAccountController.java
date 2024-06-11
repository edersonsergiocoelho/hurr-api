package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.bank.account.CustomerBankAccountDTO;
import br.com.escconsulting.dto.customer.bank.account.CustomerBankAccountSearchDTO;
import br.com.escconsulting.entity.CustomerBankAccount;
import br.com.escconsulting.mapper.CustomerBankAccountMapper;
import br.com.escconsulting.service.CustomerBankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer-bank-account")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerBankAccountController {

    private final CustomerBankAccountService customerBankAccountService;

    @GetMapping("/{customerBankAccountId}")
    public ResponseEntity<CustomerBankAccountDTO> findById(@PathVariable("customerBankAccountId") UUID customerBankAccountId) {
        return customerBankAccountService.findById(customerBankAccountId)
                .map(CustomerBankAccountMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<CustomerBankAccount>> findAll() {
        List<CustomerBankAccount> listCustomerBankAccount = customerBankAccountService.findAll();
        return ResponseEntity.ok(listCustomerBankAccount);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @CurrentUser LocalUser localUser,
            @RequestBody CustomerBankAccountSearchDTO customerBankAccountSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<CustomerBankAccountDTO> customerBankAccounts = customerBankAccountService.searchPage(localUser, customerBankAccountSearchDTO, pageable);

        return ResponseEntity.ok(customerBankAccounts);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerBankAccount customerBankAccount) {
        return customerBankAccountService.save(customerBankAccount)
                .map(CustomerBankAccountMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save customer bank account."));
    }

    @PutMapping("/{customerBankAccountId}")
    public ResponseEntity<?> update(@PathVariable("customerBankAccountId") UUID customerBankAccountId,
                                    @RequestBody CustomerBankAccount customerBankAccount) {
        return customerBankAccountService.update(customerBankAccountId, customerBankAccount)
                .map(CustomerBankAccountMapper.INSTANCE::toDTO)
                .map(updatedCustomerBankAccount -> ResponseEntity.ok(updatedCustomerBankAccount))
                .orElseThrow(() -> new IllegalStateException("Failed to update customer bank account."));
    }

    @DeleteMapping("/{customerBankAccountId}")
    public ResponseEntity<?> delete(@PathVariable("customerBankAccountId") UUID customerBankAccountId) {
        customerBankAccountService.delete(customerBankAccountId);
        return ResponseEntity.noContent().build();
    }
}