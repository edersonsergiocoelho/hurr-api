package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleDTO;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleSearchDTO;
import br.com.escconsulting.entity.CustomerVehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerVehicleCustomRepository extends JpaRepository<CustomerVehicle, UUID> {

    Page<CustomerVehicleDTO> searchPage(CustomerVehicleSearchDTO customerWithdrawalRequestSearchDTO, Pageable pageable);

    Long countSearchPage(CustomerVehicleSearchDTO customerWithdrawalRequestSearchDTO);
}