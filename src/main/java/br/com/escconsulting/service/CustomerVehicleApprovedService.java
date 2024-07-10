package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedDTO;
import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleApproved;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleApprovedService {

    Optional<CustomerVehicleApproved> findById(UUID customerVehicleApprovedId);

    List<CustomerVehicleApproved> findAll();

    Page<CustomerVehicleApprovedDTO> searchPage(LocalUser localUser, CustomerVehicleApprovedSearchDTO customerVehicleApprovedSearchDTO, Pageable pageable);

    Optional<CustomerVehicleApproved> save(CustomerVehicleApproved customerVehicleApproved);

    List<CustomerVehicleApproved> saveAll(List<CustomerVehicleApproved> customerVehicleApproveds);

    Optional<CustomerVehicleApproved> update(UUID customerVehicleApprovedId, CustomerVehicleApproved customerVehicleApproved);

    void delete(UUID customerVehicleApprovedId);
}