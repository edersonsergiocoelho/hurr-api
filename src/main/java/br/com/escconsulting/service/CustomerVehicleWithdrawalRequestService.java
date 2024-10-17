package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestDTO;
import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleWithdrawalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleWithdrawalRequestService {

    Optional<CustomerVehicleWithdrawalRequest> findById(UUID customerWithdrawalRequestId);

    List<CustomerVehicleWithdrawalRequest> findAll();

    Page<CustomerVehicleWithdrawalRequestDTO> searchPage(LocalUser localUser, CustomerVehicleWithdrawalRequestSearchDTO customerVehicleWithdrawalRequestSearchDTO, Pageable pageable);

    Optional<CustomerVehicleWithdrawalRequest> save(CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest);

    List<CustomerVehicleWithdrawalRequest> saveAll(List<CustomerVehicleWithdrawalRequest> customerVehicleWithdrawalRequests);

    Optional<CustomerVehicleWithdrawalRequest> update(UUID customerWithdrawalRequestId, CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest);

    Optional<CustomerVehicleWithdrawalRequest> approval(UUID customerWithdrawalRequestId);

    void delete(UUID customerWithdrawalRequestId);
}