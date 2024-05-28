package br.com.escconsulting.service;

import br.com.escconsulting.entity.CustomerVehicleReview;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleReviewService {

    Optional<CustomerVehicleReview> findById(UUID customerVehicleReviewId);

    Optional<CustomerVehicleReview> findByCustomerVehicleIdAndCustomerId(UUID customerVehicleId, UUID customerId);

    List<CustomerVehicleReview> findAll();

    List<CustomerVehicleReview> findAllByCustomerVehicleId(UUID customerVehicleId);

    Optional<CustomerVehicleReview> save(CustomerVehicleReview customerVehicleReview);

    Optional<CustomerVehicleReview> update(UUID customerVehicleReviewId, CustomerVehicleReview customerVehicleReview);

    void delete(UUID id);
}