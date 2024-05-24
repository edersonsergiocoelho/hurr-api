package br.com.escconsulting.service;

import br.com.escconsulting.entity.CustomerVehicleReview;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleReviewService {

    Optional<CustomerVehicleReview> findById(UUID id);

    Optional<CustomerVehicleReview> findByCustomerVehicleIdAndCustomerId(UUID customerVehicleId, UUID customerId);

    List<CustomerVehicleReview> findAll();

    List<CustomerVehicleReview> findAllByCustomerVehicleId(UUID customerVehicleId);

    Optional<CustomerVehicleReview> save(CustomerVehicleReview review);

    Optional<CustomerVehicleReview> update(UUID id, CustomerVehicleReview review);

    void delete(UUID id);
}