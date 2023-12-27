package br.com.escconsulting.controller;

import br.com.escconsulting.entity.CustomerVehicleReview;
import br.com.escconsulting.service.CustomerVehicleReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer-vehicle-review")
public class CustomerVehicleReviewController {

    private final CustomerVehicleReviewService customerVehicleReviewService;

    @Autowired
    public CustomerVehicleReviewController(CustomerVehicleReviewService customerVehicleReviewService) {
        this.customerVehicleReviewService = customerVehicleReviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerVehicleReview> findById(@PathVariable("id") UUID id) {
        CustomerVehicleReview review = customerVehicleReviewService.findById(id);
        return ResponseEntity.ok(review);
    }

    @GetMapping
    public ResponseEntity<List<CustomerVehicleReview>> findAll() {
        List<CustomerVehicleReview> reviews = customerVehicleReviewService.findAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/by/customer-vehicle/{customerVehicleId}")
    public ResponseEntity<List<CustomerVehicleReview>> findAllByCustomerVehicleId(@PathVariable("customerVehicleId") UUID customerVehicleId) {
        List<CustomerVehicleReview> reviews = customerVehicleReviewService.findAllByCustomerVehicleId(customerVehicleId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<CustomerVehicleReview> save(@RequestBody CustomerVehicleReview customerVehicleReview) {
        CustomerVehicleReview savedReview = customerVehicleReviewService.save(customerVehicleReview);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerVehicleReview> update(@PathVariable("id") UUID id, @RequestBody CustomerVehicleReview customerVehicleReview) {
        CustomerVehicleReview updatedReview = customerVehicleReviewService.update(id, customerVehicleReview);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        customerVehicleReviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}