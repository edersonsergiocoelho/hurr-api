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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer-vehicle-booking")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleBookingController {

    private final CustomerVehicleBookingService customerVehicleBookingService;

    @GetMapping("/{customerVehicleBookingId}")
    public ResponseEntity<CustomerVehicleBookingDTO> findById(@PathVariable("customerVehicleBookingId") UUID customerVehicleBookingId) {
        return customerVehicleBookingService.findById(customerVehicleBookingId)
                .map(CustomerVehicleBookingMapper.INSTANCE::toDTONoFile)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }

    @GetMapping("/mp/payment/{paymentId}")
    public ResponseEntity<CustomerVehicleBookingDTO> findByPaymentId(@PathVariable("paymentId") Long paymentId) {
        return customerVehicleBookingService.findByPaymentId(paymentId)
                .map(CustomerVehicleBookingMapper.INSTANCE::toDTONoFile)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }

    @GetMapping
    public ResponseEntity<List<CustomerVehicleBookingDTO>> findAll() {
        return ResponseEntity.ok(
                customerVehicleBookingService.findAll().stream()
                        .map(CustomerVehicleBookingMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/by/customer-vehicle/withdrawable-balance")
    public ResponseEntity<List<CustomerVehicleBookingDTO>> findByCustomerVehicleWithdrawableBalance(@CurrentUser LocalUser localUser) {
        return ResponseEntity.ok(
                customerVehicleBookingService.findByCustomerVehicleWithdrawableBalance(localUser).stream()
                        .map(CustomerVehicleBookingMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/sum/customer-vehicle/total-earnings")
    public ResponseEntity<BigDecimal> sumCustomerVehicleTotalEarnings(@CurrentUser LocalUser localUser,
                                                                      @RequestBody CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {
        return customerVehicleBookingService.sumCustomerVehicleTotalEarnings(localUser, customerVehicleBookingSearchDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }

    @PostMapping("/sum/customer-vehicle/withdrawable-current-balance")
    public ResponseEntity<BigDecimal> sumCustomerVehicleWithdrawableCurrentBalance(@CurrentUser LocalUser localUser,
                                                                                   @RequestBody CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {
        return customerVehicleBookingService.sumCustomerVehicleWithdrawableCurrentBalance(localUser, customerVehicleBookingSearchDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }

    @PostMapping("/sum/customer-vehicle/withdrawable-balance")
    public ResponseEntity<BigDecimal> sumCustomerVehicleWithdrawableBalance(@CurrentUser LocalUser localUser,
                                                                            @RequestBody CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {
        return customerVehicleBookingService.sumCustomerVehicleWithdrawableBalance(localUser, customerVehicleBookingSearchDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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

    @PostMapping("/customer-vehicle/search/page")
    public ResponseEntity<?> customerVehicleSearchPage(
            @CurrentUser LocalUser localUser,
            @RequestBody CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<CustomerVehicleBookingDTO> customerVehicleBookingsDTO = customerVehicleBookingService.customerVehicleSearchPage(localUser, customerVehicleBookingSearchDTO, pageable);

        return ResponseEntity.ok(customerVehicleBookingsDTO);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerVehicleBooking customerVehicleBooking) {
        return customerVehicleBookingService.save(customerVehicleBooking)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save customer vehicle booking."));
    }

    @PutMapping("check-in/{customerVehicleBookingId}")
    public ResponseEntity<?> checkIn(@PathVariable("customerVehicleBookingId") UUID customerVehicleBookingId,
                                             @RequestBody CustomerVehicleBooking customerVehicleBooking) {
        return customerVehicleBookingService.checkIn(customerVehicleBookingId, customerVehicleBooking)
                .map(CustomerVehicleBookingMapper.INSTANCE::toDTO)
                .map(updatedCustomerVehicleBooking -> ResponseEntity.ok(updatedCustomerVehicleBooking))
                .orElseThrow(() -> new IllegalStateException("Failed to update customer vehicle booking."));
    }

    @PutMapping("check-out/{customerVehicleBookingId}")
    public ResponseEntity<?> checkOut(@PathVariable("customerVehicleBookingId") UUID customerVehicleBookingId,
                                             @RequestBody CustomerVehicleBooking customerVehicleBooking) {
        return customerVehicleBookingService.checkOut(customerVehicleBookingId, customerVehicleBooking)
                .map(CustomerVehicleBookingMapper.INSTANCE::toDTO)
                .map(updatedCustomerVehicleBooking -> ResponseEntity.ok(updatedCustomerVehicleBooking))
                .orElseThrow(() -> new IllegalStateException("Failed to update customer vehicle booking."));
    }

    @PutMapping("/{customerVehicleBookingId}")
    public ResponseEntity<?> update(@PathVariable("customerVehicleBookingId") UUID customerVehicleBookingId,
                                    @RequestBody CustomerVehicleBooking customerVehicleBooking) {
        return customerVehicleBookingService.update(customerVehicleBookingId, customerVehicleBooking)
                .map(CustomerVehicleBookingMapper.INSTANCE::toDTO)
                .map(updatedCustomerVehicleBooking -> ResponseEntity.ok(updatedCustomerVehicleBooking))
                .orElseThrow(() -> new IllegalStateException("Failed to update customer vehicle booking."));
    }

    @PutMapping("/cancel-booking/{customerVehicleBookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable("customerVehicleBookingId") UUID customerVehicleBookingId,
                                           @RequestBody CustomerVehicleBooking customerVehicleBooking) {
        return customerVehicleBookingService.cancelBooking(customerVehicleBookingId, customerVehicleBooking)
                .map(CustomerVehicleBookingMapper.INSTANCE::toDTO)
                .map(updatedCustomerVehicleBooking -> ResponseEntity.ok(updatedCustomerVehicleBooking))
                .orElseThrow(() -> new IllegalStateException("Failed to update customer vehicle booking."));
    }

    @DeleteMapping("/{customerVehicleBookingId}")
    public ResponseEntity<?> delete(@PathVariable("customerVehicleBookingId") UUID customerVehicleBookingId) {
        customerVehicleBookingService.delete(customerVehicleBookingId);
        return ResponseEntity.noContent().build();
    }
}