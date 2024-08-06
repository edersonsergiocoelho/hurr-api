package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressDTO;
import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerVehicleAddressCustomRepository extends JpaRepository<CustomerVehicleAddress, UUID> {

    Page<CustomerVehicleAddressDTO> searchPage(CustomerVehicleAddressSearchDTO customerVehicleAddressSearchDTO, Pageable pageable);

    Long countSearchPage(CustomerVehicleAddressSearchDTO customerVehicleAddressSearchDTO);
}