package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.customer.bank.account.CustomerBankAccountDTO;
import br.com.escconsulting.dto.customer.bank.account.CustomerBankAccountSearchDTO;
import br.com.escconsulting.entity.CustomerBankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.UUID;

public interface CustomerBankAccountCustomRepository extends JpaRepository<CustomerBankAccount, UUID> {

    Page<CustomerBankAccountDTO> searchPage(CustomerBankAccountSearchDTO customerBankAccountSearchDTO, Pageable pageable);

    Page<CustomerBankAccountDTO> customerVehicleSearchPage(CustomerBankAccountSearchDTO customerBankAccountSearchDTO, Pageable pageable);

    Long countSearchPage(CustomerBankAccountSearchDTO customerBankAccountSearchDTO);

    Long countCustomerVehicleSearchPage(CustomerBankAccountSearchDTO customerBankAccountSearchDTO);
}