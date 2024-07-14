package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.customer.vehicle.file.insurance.CustomerVehicleFileInsuranceDTO;
import br.com.escconsulting.dto.customer.vehicle.file.insurance.CustomerVehicleFileInsuranceSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleFileInsurance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerVehicleFileInsuranceCustomRepository extends JpaRepository<CustomerVehicleFileInsurance, UUID> {

    Page<CustomerVehicleFileInsuranceDTO> searchPage(CustomerVehicleFileInsuranceSearchDTO customerVehicleFileInsuranceSearchDTO, Pageable pageable);

    Long countSearchPage(CustomerVehicleFileInsuranceSearchDTO customerVehicleFileInsuranceSearchDTO);
}