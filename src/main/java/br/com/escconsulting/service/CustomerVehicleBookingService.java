package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleBookingService {
    Optional<CustomerVehicleBooking> findById(UUID customerVehicleBookingId);

    boolean existsByBooking(String booking);

    List<CustomerVehicleBooking> findAll();

    Page<CustomerVehicleBooking> searchPage(LocalUser localUser, CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO, Pageable pageable);

    Optional<CustomerVehicleBooking> save(CustomerVehicleBooking customerVehicleBooking);

    Optional<CustomerVehicleBooking> update(UUID customerVehicleBookingId, CustomerVehicleBooking customerVehicleBooking);

    void delete(UUID customerVehicleBookingId);
}