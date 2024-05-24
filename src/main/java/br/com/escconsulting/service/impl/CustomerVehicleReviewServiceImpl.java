package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.CustomerVehicleReview;
import br.com.escconsulting.repository.CustomerVehicleReviewRepository;
import br.com.escconsulting.service.CustomerVehicleReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerVehicleReviewServiceImpl implements CustomerVehicleReviewService {

    private final CustomerVehicleReviewRepository customerVehicleReviewRepository;

    @Autowired
    public CustomerVehicleReviewServiceImpl(CustomerVehicleReviewRepository customerVehicleReviewRepository) {
        this.customerVehicleReviewRepository = customerVehicleReviewRepository;
    }

    @Override
    public Optional<CustomerVehicleReview> findById(UUID id) {
        return customerVehicleReviewRepository.findById(id);
    }

    @Override
    public Optional<CustomerVehicleReview> findByCustomerVehicleIdAndCustomerId(UUID customerVehicleId, UUID customerId) {
        return customerVehicleReviewRepository.findByCustomerVehicleIdAndCustomerId(customerVehicleId, customerId);
    }

    @Override
    public List<CustomerVehicleReview> findAll() {
        return customerVehicleReviewRepository.findAll();
    }

    @Override
    public List<CustomerVehicleReview> findAllByCustomerVehicleId(UUID customerVehicleId) {
        return customerVehicleReviewRepository.findAllByCustomerVehicleId(customerVehicleId);
    }

    @Override
    public Optional<CustomerVehicleReview> save(CustomerVehicleReview customerVehicleReview) {
        return Optional.of(customerVehicleReviewRepository.save(customerVehicleReview));
    }

    @Override
    public Optional<CustomerVehicleReview> update(UUID id, CustomerVehicleReview customerVehicleReview) {
        return findById(id)
                .map(existingCustomerVehicleReview -> {

                    existingCustomerVehicleReview.setReview(customerVehicleReview.getReview());
                    existingCustomerVehicleReview.setRating(customerVehicleReview.getRating());

                    return customerVehicleReviewRepository.save(existingCustomerVehicleReview);
                });
    }

    @Override
    public void delete(UUID customerVehicleReviewId) {
        findById(customerVehicleReviewId).ifPresent(customerVehicleReviewRepository::delete);
    }
}