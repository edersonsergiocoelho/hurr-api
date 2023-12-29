package br.com.escconsulting.service;

import br.com.escconsulting.entity.CustomerVehicleReview;

import java.util.List;
import java.util.UUID;

public interface CustomerVehicleReviewService {
    CustomerVehicleReview findById(UUID id);

    List<CustomerVehicleReview> findAll();

    List<CustomerVehicleReview> findAllByCustomerVehicleId(UUID customerVehicleId);

    CustomerVehicleReview save(CustomerVehicleReview review);

    CustomerVehicleReview update(UUID id, CustomerVehicleReview review);

    void delete(UUID id);
}