package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerVehicleBookingCustomRepository extends JpaRepository<CustomerVehicleBooking, UUID> {

    Page<CustomerVehicleBooking> searchPage(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO, Pageable pageable);

    Page<CustomerVehicleBooking> customerVehicleSearchPage(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO, Pageable pageable);

    Long countSearchPage(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO);

    Long countCustomerVehicleSearchPage(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO);
}