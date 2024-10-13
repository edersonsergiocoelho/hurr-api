package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.bank.account.CustomerVehicleBankAccountDTO;
import br.com.escconsulting.dto.customer.vehicle.bank.account.CustomerVehicleBankAccountSearchDTO;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.CustomerVehicleBankAccount;
import br.com.escconsulting.repository.CustomerVehicleBankAccountRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleBankAccountCustomRepository;
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

    private final CustomerVehicleBankAccountRepository customerVehicleBankAccountRepository;

    private final CustomerVehicleBankAccountCustomRepository customerVehicleBankAccountCustomRepository;

    @Transactional
    @Override
    public Optional<CustomerVehicleBankAccount> findById(UUID customerBankAccountId) {

        return Optional.ofNullable(customerVehicleBankAccountRepository.findById(customerBankAccountId)
                .orElseThrow(() -> new RuntimeException("CustomerBankAccount not found with customerBankAccountId: " + customerBankAccountId)));
    }

    @Transactional
    @Override
    public List<CustomerVehicleBankAccount> findAll() {
        return customerVehicleBankAccountRepository.findAll();
    }

    @Transactional
    @Override
    public Page<CustomerVehicleBankAccountDTO> searchPage(LocalUser localUser, CustomerVehicleBankAccountSearchDTO customerVehicleBankAccountSearchDTO, Pageable pageable) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            customerVehicleBankAccountSearchDTO.setCustomerId(customer.getCustomerId());
            return customerVehicleBankAccountCustomRepository.searchPage(customerVehicleBankAccountSearchDTO, pageable);
        }).orElseThrow(() -> new RuntimeException("Customer not found for email: " + localUser.getUsername()));
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleBankAccount> save(CustomerVehicleBankAccount customerVehicleBankAccount) {

        customerVehicleBankAccount.setCreatedDate(Instant.now());
        customerVehicleBankAccount.setEnabled(Boolean.TRUE);

        return Optional.of(customerVehicleBankAccountRepository.save(customerVehicleBankAccount));
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleBankAccount> update(UUID customerBankAccountId, CustomerVehicleBankAccount customerVehicleBankAccount) {
        return findById(customerBankAccountId)
                .map(existingCustomerBankAccount -> {

                    existingCustomerBankAccount.setEnabled(customerVehicleBankAccount.getEnabled());
                    existingCustomerBankAccount.setModifiedDate(Instant.now());

                    return customerVehicleBankAccountRepository.save(existingCustomerBankAccount);
                });
    }

    @Transactional
    @Override
    public void delete(UUID customerBankAccountId) {
        findById(customerBankAccountId).ifPresent(customerVehicleBankAccountRepository::delete);
    }
}