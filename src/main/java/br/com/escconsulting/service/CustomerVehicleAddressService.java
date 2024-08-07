package br.com.escconsulting.service;

import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressDTO;
import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressSaveAddressDTO;
import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressSearchDTO;
import br.com.escconsulting.entity.CustomerAddress;
import br.com.escconsulting.entity.CustomerVehicleAddress;
import org.antlr.v4.runtime.misc.MultiMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleAddressService {
    
    CustomerVehicleAddress findById(UUID id);

    List<CustomerVehicleAddress> findAll();

    List<CustomerVehicleAddress> findAllByCustomerVehicleId(UUID customerVehicleId);

    List<CustomerVehicleAddress> findAllByCustomerVehicleIdAndAddressType(UUID customerVehicleId, String addressTypeName);

    Page<CustomerVehicleAddressDTO> searchPage(CustomerVehicleAddressSearchDTO customerVehicleAddressSearchDTO, Pageable pageable);

    Optional<CustomerVehicleAddress> save(CustomerVehicleAddress customerVehicleAddress);

    Optional<CustomerVehicleAddress> saveAddress(CustomerVehicleAddressSaveAddressDTO customerVehicleAddressSaveAddressDTO);

    CustomerVehicleAddress update(UUID customerVehicleAddressId, CustomerVehicleAddress customerVehicleAddress);

    CustomerVehicleAddress updateAddress(UUID customerVehicleAddressId, CustomerVehicleAddressSaveAddressDTO customerVehicleAddressSaveAddressDTO);

    void delete(UUID id);
}