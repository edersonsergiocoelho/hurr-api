package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.bank.BankDTO;
import br.com.escconsulting.dto.bank.BankSearchDTO;
import br.com.escconsulting.entity.Bank;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.repository.BankRepository;
import br.com.escconsulting.repository.custom.BankCustomRepository;
import br.com.escconsulting.service.BankService;
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
public class BankServiceImpl implements BankService {

    private final CustomerService customerService;

    private final BankRepository bankRepository;

    private final BankCustomRepository bankCustomRepository;

    @Transactional
    @Override
    public Optional<Bank> findById(UUID bankId) {

        return Optional.ofNullable(bankRepository.findById(bankId)
                .orElseThrow(() -> new RuntimeException("Bank not found with bankId: " + bankId)));
    }

    @Transactional
    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    @Transactional
    @Override
    public Page<BankDTO> searchPage(LocalUser localUser, BankSearchDTO bankSearchDTO, Pageable pageable) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            bankSearchDTO.setCustomerId(customer.getCustomerId());
            return bankCustomRepository.searchPage(bankSearchDTO, pageable);
        }).orElseThrow(() -> new RuntimeException("Customer not found for email: " + localUser.getUsername()));
    }

    @Transactional
    @Override
    public Optional<Bank> save(Bank bank) {

        bank.setCreatedDate(Instant.now());
        bank.setEnabled(Boolean.TRUE);

        return Optional.of(bankRepository.save(bank));
    }

    @Transactional
    @Override
    public Optional<Bank> update(UUID bankId, Bank bank) {
        return findById(bankId)
                .map(existingBank -> {

                    existingBank.setEnabled(bank.getEnabled());
                    existingBank.setModifiedDate(Instant.now());

                    return bankRepository.save(existingBank);
                });
    }

    @Transactional
    @Override
    public void delete(UUID bankId) {
        findById(bankId).ifPresent(bankRepository::delete);
    }
}