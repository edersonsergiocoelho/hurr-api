package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.customer.vehicle.bank.account.CustomerVehicleBankAccountDTO;
import br.com.escconsulting.dto.customer.vehicle.bank.account.CustomerVehicleBankAccountSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleBankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerVehicleBankAccountCustomRepository extends JpaRepository<CustomerVehicleBankAccount, UUID> {

    Page<CustomerVehicleBankAccountDTO> searchPage(CustomerVehicleBankAccountSearchDTO customerVehicleBankAccountSearchDTO, Pageable pageable);

    Long countSearchPage(CustomerVehicleBankAccountSearchDTO customerVehicleBankAccountSearchDTO);
}