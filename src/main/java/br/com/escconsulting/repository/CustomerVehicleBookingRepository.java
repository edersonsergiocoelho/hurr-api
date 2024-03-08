package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicleBooking;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerVehicleBookingRepository extends JpaRepository<CustomerVehicleBooking, UUID> {
    @EntityGraph(attributePaths = {
            "customer",
            "customerVehicle",
            "customerVehicle.vehicle",
            "customerVehicle.vehicle.vehicleBrand",
            "customerVehicle.customer",
            "customerVehicle.vehicle",
            "customerVehicle.vehicleModel",
            "customerVehicle.vehicleModel.vehicleCategory",
            "customerVehicle.vehicleColor",
            "customerVehicle.vehicleFuelType",
            "customerVehicle.vehicleTransmission",
            "customerVehicle.addresses",
            "customerVehicle.addresses.address",
            "customerVehicle.addresses.address.country",
            "customerVehicle.addresses.address.state",
            "customerVehicle.addresses.address.city"
    })
    Optional<CustomerVehicleBooking> findById(UUID customerVehicleBookingId);

    boolean existsByBooking(String booking);
}