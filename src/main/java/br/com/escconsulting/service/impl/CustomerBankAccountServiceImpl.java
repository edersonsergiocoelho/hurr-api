package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.bank.account.CustomerBankAccountDTO;
import br.com.escconsulting.dto.customer.bank.account.CustomerBankAccountSearchDTO;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.CustomerBankAccount;
import br.com.escconsulting.repository.CustomerBankAccountRepository;
import br.com.escconsulting.repository.custom.CustomerBankAccountCustomRepository;
import br.com.escconsulting.service.CustomerBankAccountService;
import br.com.escconsulting.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerBankAccountServiceImpl implements CustomerBankAccountService {

    private final CustomerService customerService;

    private final CustomerBankAccountRepository customerBankAccountRepository;

    private final CustomerBankAccountCustomRepository customerBankAccountCustomRepository;

    @Transactional
    @Override
    public Optional<CustomerBankAccount> findById(UUID customerBankAccountId) {

        return Optional.ofNullable(customerBankAccountRepository.findById(customerBankAccountId)
                .orElseThrow(() -> new RuntimeException("CustomerBankAccount not found with customerBankAccountId: " + customerBankAccountId)));
    }

    @Transactional
    @Override
    public List<CustomerBankAccount> findAll() {
        return customerBankAccountRepository.findAll();
    }

    @Transactional
    @Override
    public Page<CustomerBankAccountDTO> searchPage(LocalUser localUser, CustomerBankAccountSearchDTO customerBankAccountSearchDTO, Pageable pageable) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            customerBankAccountSearchDTO.setCustomerId(customer.getCustomerId());
            return customerBankAccountCustomRepository.searchPage(customerBankAccountSearchDTO, pageable);
        }).orElseThrow(() -> new RuntimeException("Customer not found for email: " + localUser.getUsername()));
    }

    @Transactional
    @Override
    public Page<CustomerBankAccountDTO> customerVehicleSearchPage(LocalUser localUser, CustomerBankAccountSearchDTO customerBankAccountSearchDTO, Pageable pageable) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            customerBankAccountSearchDTO.setCustomerId(customer.getCustomerId());
            return customerBankAccountCustomRepository.customerVehicleSearchPage(customerBankAccountSearchDTO, pageable);
        }).orElseThrow(() -> new RuntimeException("Customer not found for email: " + localUser.getUsername()));
    }

    @Transactional
    @Override
    public Optional<CustomerBankAccount> save(CustomerBankAccount customerBankAccount) {

        customerBankAccount.setCreatedDate(Instant.now());
        customerBankAccount.setEnabled(Boolean.TRUE);

        return Optional.of(customerBankAccountRepository.save(customerBankAccount));
    }

    @Transactional
    @Override
    public Optional<CustomerBankAccount> update(UUID customerBankAccountId, CustomerBankAccount customerBankAccount) {
        return findById(customerBankAccountId)
                .map(existingCustomerBankAccount -> {

                    existingCustomerBankAccount.setEnabled(customerBankAccount.getEnabled());
                    existingCustomerBankAccount.setModifiedDate(Instant.now());

                    return customerBankAccountRepository.save(existingCustomerBankAccount);
                });
    }

    @Transactional
    @Override
    public void delete(UUID customerBankAccountId) {
        findById(customerBankAccountId).ifPresent(customerBankAccountRepository::delete);
    }
}