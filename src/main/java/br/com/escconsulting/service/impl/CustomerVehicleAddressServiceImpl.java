package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.CustomerVehicleAddress;
import br.com.escconsulting.repository.CustomerVehicleAddressRepository;
import br.com.escconsulting.service.CustomerVehicleAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerVehicleAddressServiceImpl implements CustomerVehicleAddressService {

    private final CustomerVehicleAddressRepository customerVehicleAddressRepository;

    @Autowired
    public CustomerVehicleAddressServiceImpl(CustomerVehicleAddressRepository customerVehicleAddressRepository) {
        this.customerVehicleAddressRepository = customerVehicleAddressRepository;
    }

    @Override
    public CustomerVehicleAddress findById(UUID id) {
        return customerVehicleAddressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
    }

    @Override
    public List<CustomerVehicleAddress> findAll() {
        return customerVehicleAddressRepository.findAll();
    }

    @Override
    public List<CustomerVehicleAddress> findAllByCustomerVehicleId(UUID customerVehicleId) {
        return customerVehicleAddressRepository.findAllByCustomerVehicleId(customerVehicleId);
    }

    @Override
    public List<CustomerVehicleAddress> findAllByCustomerVehicleIdAndAddressType(UUID customerVehicleId, String addressType) {
        return customerVehicleAddressRepository.findAllByCustomerVehicleIdAndAddressType(customerVehicleId, addressType);
    }

    @Override
    public CustomerVehicleAddress save(CustomerVehicleAddress review) {
        return customerVehicleAddressRepository.save(review);
    }

    @Override
    public CustomerVehicleAddress update(UUID id, CustomerVehicleAddress customerVehicleAddress) {
        CustomerVehicleAddress existingReview = findById(id);

        return customerVehicleAddressRepository.save(existingReview);
    }

    @Override
    public void delete(UUID id) {
        CustomerVehicleAddress review = findById(id);
        customerVehicleAddressRepository.delete(review);
    }
}