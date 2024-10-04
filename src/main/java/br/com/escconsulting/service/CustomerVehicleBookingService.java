package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingDTO;
import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleBookingService {

    Optional<CustomerVehicleBooking> findById(UUID customerVehicleBookingId);

    Optional<CustomerVehicleBooking> findByPaymentId(Long customerVehicleBookingId);

    boolean existsByBooking(String booking);

    List<CustomerVehicleBooking> findAll();

    List<CustomerVehicleBooking> findByCustomerVehicleWithdrawableBalance(LocalUser localUser);

    Optional<BigDecimal> sumCustomerVehicleTotalEarnings(LocalUser localUser, CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO);

    Optional<BigDecimal> sumCustomerVehicleWithdrawableCurrentBalance(LocalUser localUser, CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO);

    Optional<BigDecimal> sumCustomerVehicleWithdrawableBalance(LocalUser localUser, CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO);

    Page<CustomerVehicleBookingDTO> searchPage(LocalUser localUser, CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO, Pageable pageable);

    Page<CustomerVehicleBookingDTO> customerVehicleSearchPage(LocalUser localUser, CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO, Pageable pageable);

    Optional<CustomerVehicleBooking> save(CustomerVehicleBooking customerVehicleBooking);

    Optional<CustomerVehicleBooking> checkIn(UUID customerVehicleBookingId, CustomerVehicleBooking customerVehicleBooking);

    Optional<CustomerVehicleBooking> checkOut(UUID customerVehicleBookingId, CustomerVehicleBooking customerVehicleBooking);

    Optional<CustomerVehicleBooking> update(UUID customerVehicleBookingId, CustomerVehicleBooking customerVehicleBooking);

    Optional<CustomerVehicleBooking> cancelBooking(UUID customerVehicleBookingId, CustomerVehicleBooking customerVehicleBooking);

    void delete(UUID customerVehicleBookingId);
}