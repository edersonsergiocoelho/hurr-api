package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestDTO;
import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleWithdrawalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerVehicleWithdrawalRequestCustomRepository extends JpaRepository<CustomerVehicleWithdrawalRequest, UUID> {

    Page<CustomerVehicleWithdrawalRequestDTO> searchPage(CustomerVehicleWithdrawalRequestSearchDTO customerWithdrawalRequestSearchDTO, Pageable pageable);

    Long countSearchPage(CustomerVehicleWithdrawalRequestSearchDTO customerWithdrawalRequestSearchDTO);
}