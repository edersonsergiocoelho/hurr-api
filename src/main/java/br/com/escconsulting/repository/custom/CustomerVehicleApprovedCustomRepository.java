package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedDTO;
import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleApproved;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerVehicleApprovedCustomRepository extends JpaRepository<CustomerVehicleApproved, UUID> {

    Page<CustomerVehicleApprovedDTO> searchPage(CustomerVehicleApprovedSearchDTO customerVehicleApprovedSearchDTO, Pageable pageable);

    Long countSearchPage(CustomerVehicleApprovedSearchDTO customerVehicleApprovedSearchDTO);
}