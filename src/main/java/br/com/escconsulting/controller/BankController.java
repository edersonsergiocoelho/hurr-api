package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingDTO;
import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleBooking;
import br.com.escconsulting.mapper.CustomerVehicleBookingMapper;
import br.com.escconsulting.service.CustomerVehicleBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BankController {

    private final CustomerVehicleBookingService customerVehicleBookingService;

    @GetMapping("/{bankId}")
    public ResponseEntity<CustomerVehicleBookingDTO> findById(@PathVariable("bankId") UUID bankId) {
        return customerVehicleBookingService.findById(bankId)
                .map(CustomerVehicleBookingMapper.INSTANCE::toDTO)
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
            @CurrentUser LocalUser localUser,
            @RequestBody CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<CustomerVehicleBookingDTO> customerVehicleBookings = customerVehicleBookingService.searchPage(localUser, customerVehicleBookingSearchDTO, pageable);

        return ResponseEntity.ok(customerVehicleBookings);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerVehicleBooking customerVehicleBooking) {
        return customerVehicleBookingService.save(customerVehicleBooking)
                .map(CustomerVehicleBookingMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save bank."));
    }

    @PutMapping("/{bankId}")
    public ResponseEntity<?> update(@PathVariable("bankId") UUID bankId,
                                    @RequestBody CustomerVehicleBooking customerVehicleBooking) {
        return customerVehicleBookingService.update(bankId, customerVehicleBooking)
                .map(CustomerVehicleBookingMapper.INSTANCE::toDTO)
                .map(updatedCustomerVehicleBooking -> ResponseEntity.ok(updatedCustomerVehicleBooking))
                .orElseThrow(() -> new IllegalStateException("Failed to update bank."));
    }

    @DeleteMapping("/{bankId}")
    public ResponseEntity<?> delete(@PathVariable("bankId") UUID bankId) {
        customerVehicleBookingService.delete(bankId);
        return ResponseEntity.noContent().build();
    }
}