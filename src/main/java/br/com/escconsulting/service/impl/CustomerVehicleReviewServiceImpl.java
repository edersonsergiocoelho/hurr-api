package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.CustomerVehicleReview;
import br.com.escconsulting.repository.CustomerVehicleReviewRepository;
import br.com.escconsulting.service.CustomerVehicleReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Optional<CustomerVehicleReview> findById(UUID customerVehicleReviewId) {
        return customerVehicleReviewRepository.findById(customerVehicleReviewId);
    }

    @Override
    @Transactional
    public Optional<CustomerVehicleReview> findByCustomerVehicleBookingIdAndCustomerId(UUID customerVehicleBookingId, UUID customerId) {
        return customerVehicleReviewRepository.findByCustomerVehicleBookingIdAndCustomerId(customerVehicleBookingId, customerId);
    }

    @Override
    @Transactional
    public List<CustomerVehicleReview> findAll() {
        return customerVehicleReviewRepository.findAll();
    }

    @Override
    @Transactional
    public List<CustomerVehicleReview> findAllByCustomerVehicleId(UUID customerVehicleId) {
        return customerVehicleReviewRepository.findAllByCustomerVehicleId(customerVehicleId);
    }

    @Override
    @Transactional
    public Optional<CustomerVehicleReview> save(CustomerVehicleReview customerVehicleReview) {
        return Optional.of(customerVehicleReviewRepository.save(customerVehicleReview));
    }

    @Override
    @Transactional
    public Optional<CustomerVehicleReview> update(UUID customerVehicleReviewId, CustomerVehicleReview customerVehicleReview) {
        return findById(customerVehicleReviewId)
                .map(existingCustomerVehicleReview -> {

                    existingCustomerVehicleReview.setReview(customerVehicleReview.getReview());
                    existingCustomerVehicleReview.setRating(customerVehicleReview.getRating());

                    return customerVehicleReviewRepository.save(existingCustomerVehicleReview);
                });
    }

    @Override
    @Transactional
    public void delete(UUID customerVehicleReviewId) {
        findById(customerVehicleReviewId).ifPresent(customerVehicleReviewRepository::delete);
    }
}