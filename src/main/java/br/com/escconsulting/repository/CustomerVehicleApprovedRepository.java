package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicleApproved;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerVehicleApprovedRepository extends JpaRepository<CustomerVehicleApproved, UUID> {

    @EntityGraph(attributePaths = {
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
            "customerVehicle.addresses.address.city",
            "customerVehicle.addresses.address.addressTypes"
    })
    Optional<CustomerVehicleApproved> findById(UUID customerVehicleApprovedId);
}