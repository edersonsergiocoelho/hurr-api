package br.com.escconsulting.service;

import br.com.escconsulting.entity.CustomerVehicleAddress;
import br.com.escconsulting.entity.enumeration.AddressType;

import java.util.List;
import java.util.UUID;

public interface CustomerVehicleAddressService {
    
    CustomerVehicleAddress findById(UUID id);

    List<CustomerVehicleAddress> findAll();

    List<CustomerVehicleAddress> findAllByCustomerVehicleId(UUID customerVehicleId);

    List<CustomerVehicleAddress> findAllByCustomerVehicleIdAndAddressType(UUID customerVehicleId, AddressType addressType);

    CustomerVehicleAddress save(CustomerVehicleAddress review);

    CustomerVehicleAddress update(UUID id, CustomerVehicleAddress review);

    void delete(UUID id);
}