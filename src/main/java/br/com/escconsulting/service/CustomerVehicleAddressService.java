package br.com.escconsulting.service;

import br.com.escconsulting.entity.CustomerVehicleAddress;

import java.util.List;
import java.util.UUID;

public interface CustomerVehicleAddressService {
    
    CustomerVehicleAddress findById(UUID id);

    List<CustomerVehicleAddress> findAll();

    List<CustomerVehicleAddress> findAllByCustomerVehicleId(UUID customerVehicleId);

    List<CustomerVehicleAddress> findAllByCustomerVehicleIdAndAddressType(UUID customerVehicleId, String addressTypeName);

    CustomerVehicleAddress save(CustomerVehicleAddress review);

    CustomerVehicleAddress update(UUID id, CustomerVehicleAddress review);

    void delete(UUID id);
}