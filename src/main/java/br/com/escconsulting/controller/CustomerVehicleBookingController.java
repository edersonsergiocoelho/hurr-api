package br.com.escconsulting.controller;

import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleBooking;
import br.com.escconsulting.service.CustomerVehicleBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customer-vehicle-booking")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleBookingController {

    private final CustomerVehicleBookingService customerVehicleBookingService;

    @GetMapping("/{customerVehicleBookingId}")
    public ResponseEntity<CustomerVehicleBooking> findById(@PathVariable("customerVehicleBookingId") UUID customerVehicleBookingId) {
        return customerVehicleBookingService.findById(customerVehicleBookingId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<CustomerVehicleBooking>> findAll() {
        List<CustomerVehicleBooking> listCustomerVehicleBooking = customerVehicleBookingService.findAll();
        return ResponseEntity.ok(listCustomerVehicleBooking);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<CustomerVehicleBooking> countries = customerVehicleBookingService.searchPage(customerVehicleBookingSearchDTO, pageable);

        return ResponseEntity.ok(countries);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerVehicleBooking customerVehicleBooking) {
        return customerVehicleBookingService.save(customerVehicleBooking)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save customer vehicle booking."));
    }

    @PutMapping("/{customerVehicleBookingId}")
    public ResponseEntity<?> update(@PathVariable("customerVehicleBookingId") UUID customerVehicleBookingId,
                                    @RequestBody CustomerVehicleBooking customerVehicleBooking) {
        return customerVehicleBookingService.update(customerVehicleBookingId, customerVehicleBooking)
                .map(updatedCustomerVehicleBooking -> ResponseEntity.ok(updatedCustomerVehicleBooking))
                .orElseThrow(() -> new IllegalStateException("Failed to update customer vehicle booking."));
    }

    @DeleteMapping("/{customerVehicleBookingId}")
    public ResponseEntity<?> delete(@PathVariable("customerVehicleBookingId") UUID customerVehicleBookingId) {
        customerVehicleBookingService.delete(customerVehicleBookingId);
        return ResponseEntity.noContent().build();
    }
}