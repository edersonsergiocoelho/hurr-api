package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.CustomerVehicleReview;
import br.com.escconsulting.repository.CustomerVehicleReviewRepository;
import br.com.escconsulting.service.CustomerVehicleReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerVehicleReviewServiceImpl implements CustomerVehicleReviewService {

    private final CustomerVehicleReviewRepository customerVehicleReviewRepository;

    @Autowired
    public CustomerVehicleReviewServiceImpl(CustomerVehicleReviewRepository customerVehicleReviewRepository) {
        this.customerVehicleReviewRepository = customerVehicleReviewRepository;
    }

    @Override
    public CustomerVehicleReview findById(UUID id) {
        return customerVehicleReviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
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
    public CustomerVehicleReview save(CustomerVehicleReview review) {
        return customerVehicleReviewRepository.save(review);
    }

    @Override
    public CustomerVehicleReview update(UUID id, CustomerVehicleReview review) {
        CustomerVehicleReview existingReview = findById(id);
        existingReview.setReview(review.getReview());
        existingReview.setRating(review.getRating());

        return customerVehicleReviewRepository.save(existingReview);
    }

    @Override
    public void delete(UUID id) {
        CustomerVehicleReview review = findById(id);
        customerVehicleReviewRepository.delete(review);
    }
}