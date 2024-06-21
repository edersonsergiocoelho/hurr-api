package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.bank.account.CustomerBankAccountDTO;
import br.com.escconsulting.dto.customer.bank.account.CustomerBankAccountSearchDTO;
import br.com.escconsulting.entity.CustomerBankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerBankAccountService {

    Optional<CustomerBankAccount> findById(UUID customerBankAccountId);

    List<CustomerBankAccount> findAll();

    Page<CustomerBankAccountDTO> searchPage(LocalUser localUser, CustomerBankAccountSearchDTO customerBankAccountSearchDTO, Pageable pageable);

    Optional<CustomerBankAccount> save(CustomerBankAccount customerBankAccount);

    Optional<CustomerBankAccount> update(UUID customerBankAccountId, CustomerBankAccount customerBankAccount);

    void delete(UUID customerBankAccountId);
}