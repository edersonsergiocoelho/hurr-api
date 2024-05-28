package br.com.escconsulting.controller;

import br.com.escconsulting.dto.customer.vehicle.review.CustomerVehicleReviewDTO;
import br.com.escconsulting.entity.CustomerVehicleReview;
import br.com.escconsulting.mapper.customer.vehicle.review.CustomerVehicleReviewMapper;
import br.com.escconsulting.service.CustomerVehicleReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer-vehicle-review")
public class CustomerVehicleReviewController {

    private final CustomerVehicleReviewService customerVehicleReviewService;

    @Autowired
    public CustomerVehicleReviewController(CustomerVehicleReviewService customerVehicleReviewService) {
        this.customerVehicleReviewService = customerVehicleReviewService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerVehicleReviewDTO> findById(@PathVariable("id") UUID id) {
        return customerVehicleReviewService.findById(id)
                .map(CustomerVehicleReviewMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/by/customer-vehicle/{customerVehicleId}/customer/{customerId}")
    public ResponseEntity<CustomerVehicleReviewDTO> findByCustomerVehicleIdAndCustomerId(@PathVariable("customerVehicleId") UUID customerVehicleId,
                                                                                         @PathVariable("customerId") UUID customerId) {

        return customerVehicleReviewService.findByCustomerVehicleIdAndCustomerId(customerVehicleId, customerId)
                .map(CustomerVehicleReviewMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<CustomerVehicleReviewDTO>> findAll() {
        return ResponseEntity.ok(
                customerVehicleReviewService.findAll()
                        .stream()
                        .map(CustomerVehicleReviewMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/all/by/customer-vehicle/{customerVehicleId}")
    public ResponseEntity<List<CustomerVehicleReviewDTO>> findAllByCustomerVehicleId(@PathVariable("customerVehicleId") UUID customerVehicleId) {
        return ResponseEntity.ok(
                customerVehicleReviewService.findAllByCustomerVehicleId(customerVehicleId)
                        .stream()
                        .map(CustomerVehicleReviewMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<CustomerVehicleReviewDTO> save(@RequestBody CustomerVehicleReview customerVehicleReview) {
        return customerVehicleReviewService.save(customerVehicleReview)
                .map(savedReview -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(CustomerVehicleReviewMapper.INSTANCE.toDTO(savedReview)))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("/{customerVehicleReviewId}")
    public ResponseEntity<CustomerVehicleReviewDTO> update(@PathVariable("customerVehicleReviewId") UUID customerVehicleReviewId, @RequestBody CustomerVehicleReview customerVehicleReview) {
        return customerVehicleReviewService.update(customerVehicleReviewId, customerVehicleReview)
                .map(updatedReview -> ResponseEntity.ok(CustomerVehicleReviewMapper.INSTANCE.toDTO(updatedReview)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{customerVehicleReviewId}")
    public ResponseEntity<Void> delete(@PathVariable("customerVehicleReviewId") UUID id) {
        customerVehicleReviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}