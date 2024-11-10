package br.com.escconsulting.controller;

import br.com.escconsulting.dto.customer.vehicle.review.CustomerVehicleReviewDTO;
import br.com.escconsulting.entity.CustomerVehicleReview;
import br.com.escconsulting.mapper.CustomerVehicleReviewMapper;
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

    @GetMapping("/{customerVehicleReviewId}")
    public ResponseEntity<CustomerVehicleReviewDTO> findById(@PathVariable("customerVehicleReviewId") UUID customerVehicleReviewId) {
        return customerVehicleReviewService.findById(customerVehicleReviewId)
                .map(CustomerVehicleReviewMapper.INSTANCE::toDTONoCustomerVehicleBooking)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }

    @GetMapping("/by/customer-vehicle-booking/{customerVehicleBookingId}/customer/{customerId}")
    public ResponseEntity<CustomerVehicleReviewDTO> findByCustomerVehicleBookingIdAndCustomerId(@PathVariable("customerVehicleBookingId") UUID customerVehicleBookingId,
                                                                                                @PathVariable("customerId") UUID customerId) {

        return customerVehicleReviewService.findByCustomerVehicleBookingIdAndCustomerId(customerVehicleBookingId, customerId)
                .map(CustomerVehicleReviewMapper.INSTANCE::toDTONoCustomerVehicleBooking)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }

    @GetMapping
    public ResponseEntity<List<CustomerVehicleReviewDTO>> findAll() {
        return ResponseEntity.ok(
                customerVehicleReviewService.findAll()
                        .stream()
                        .map(CustomerVehicleReviewMapper.INSTANCE::toDTONoCustomerVehicleBooking)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/all/by/customer-vehicle/{customerVehicleId}")
    public ResponseEntity<List<CustomerVehicleReviewDTO>> findAllByCustomerVehicleId(@PathVariable("customerVehicleId") UUID customerVehicleId) {
        return ResponseEntity.ok(
                customerVehicleReviewService.findAllByCustomerVehicleId(customerVehicleId)
                        .stream()
                        .map(CustomerVehicleReviewMapper.INSTANCE::toDTONoCustomerVehicleBooking)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<CustomerVehicleReviewDTO> save(@RequestBody CustomerVehicleReview customerVehicleReview) {
        return customerVehicleReviewService.save(customerVehicleReview)
                .map(savedReview -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(CustomerVehicleReviewMapper.INSTANCE.toDTONoCustomerVehicleBooking(savedReview)))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PutMapping("/{customerVehicleReviewId}")
    public ResponseEntity<CustomerVehicleReviewDTO> update(@PathVariable("customerVehicleReviewId") UUID customerVehicleReviewId, @RequestBody CustomerVehicleReview customerVehicleReview) {
        return customerVehicleReviewService.update(customerVehicleReviewId, customerVehicleReview)
                .map(updatedReview -> ResponseEntity.ok(CustomerVehicleReviewMapper.INSTANCE.toDTONoCustomerVehicleBooking(updatedReview)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{customerVehicleReviewId}")
    public ResponseEntity<Void> delete(@PathVariable("customerVehicleReviewId") UUID id) {
        customerVehicleReviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}