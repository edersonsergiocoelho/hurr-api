package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicleBooking;
import org.glassfish.jersey.internal.inject.Custom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerVehicleBookingRepository extends JpaRepository<CustomerVehicleBooking, UUID> {

    boolean existsByBooking(String booking);

    boolean existsByMpPaymentId(Long mpPaymentId);
}