package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.bank.account.CustomerVehicleBankAccountDTO;
import br.com.escconsulting.dto.customer.vehicle.bank.account.CustomerVehicleBankAccountSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleBankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleBankAccountService {

    Optional<CustomerVehicleBankAccount> findById(UUID customerBankAccountId);

    List<CustomerVehicleBankAccount> findAll();

    Page<CustomerVehicleBankAccountDTO> searchPage(LocalUser localUser, CustomerVehicleBankAccountSearchDTO customerVehicleBankAccountSearchDTO, Pageable pageable);

    Optional<CustomerVehicleBankAccount> save(LocalUser localUser, CustomerVehicleBankAccount customerVehicleBankAccount);

    Optional<CustomerVehicleBankAccount> update(UUID customerBankAccountId, CustomerVehicleBankAccount customerVehicleBankAccount);

    void delete(UUID customerBankAccountId);
}