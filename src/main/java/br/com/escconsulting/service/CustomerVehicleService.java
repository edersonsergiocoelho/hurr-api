package br.com.escconsulting.service;

import br.com.escconsulting.dto.customer.vehicle.SearchCustomerVehicle;
import br.com.escconsulting.entity.CustomerVehicle;
import br.com.escconsulting.entity.CustomerVehicle;
import br.com.escconsulting.entity.State;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleService {

    Optional<CustomerVehicle> findById(UUID customerVehicleId);

    List<CustomerVehicle> findAll();
    
    List<CustomerVehicle> search(SearchCustomerVehicle searchCustomerVehicle);

    Optional<CustomerVehicle> save(CustomerVehicle customerVehicle);

    Optional<CustomerVehicle> update(UUID customerVehicleId, CustomerVehicle customerVehicle);

    void delete(UUID customerVehicleId);
}