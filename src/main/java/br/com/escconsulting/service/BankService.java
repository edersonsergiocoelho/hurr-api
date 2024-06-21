package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.bank.BankDTO;
import br.com.escconsulting.dto.bank.BankSearchDTO;
import br.com.escconsulting.entity.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankService {

    Optional<Bank> findById(UUID bankId);

    List<Bank> findAll();

    Page<BankDTO> searchPage(LocalUser localUser, BankSearchDTO bankSearchDTO, Pageable pageable);

    Optional<Bank> save(Bank bank);

    Optional<Bank> update(UUID bankId, Bank bank);

    void delete(UUID bankId);
}