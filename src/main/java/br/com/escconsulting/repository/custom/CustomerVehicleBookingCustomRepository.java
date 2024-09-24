package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingDTO;
import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleBookingCustomRepository extends JpaRepository<CustomerVehicleBooking, UUID> {

    Optional<CustomerVehicleBooking> findById(UUID customerId);

    List<CustomerVehicleBooking> findByCustomerVehicleWithdrawableBalance(UUID customerId);

    Page<CustomerVehicleBookingDTO> searchPage(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO, Pageable pageable);

    Page<CustomerVehicleBookingDTO> customerVehicleSearchPage(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO, Pageable pageable);

    Long countSearchPage(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO);

    Long countCustomerVehicleSearchPage(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO);

    BigDecimal sumCustomerVehicleTotalEarnings(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO);

    BigDecimal sumCustomerVehicleWithdrawableCurrentBalance(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO);

    BigDecimal sumCustomerVehicleWithdrawableBalance(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO);

    BigDecimal sumCustomerVehicleWithdrawableBalanceUnpaid(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO);

    BigDecimal withdrawableBalance(CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO);
}