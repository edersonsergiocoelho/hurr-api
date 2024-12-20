package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicle;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerVehicleRepository extends JpaRepository<CustomerVehicle, UUID> {

    @EntityGraph(attributePaths = {
            "customer",
            "vehicle",
            "vehicle.vehicleBrand",
            "vehicle.vehicleBrand.file",
            "vehicleModel",
            "vehicleModel.vehicleCategory",
            "vehicleModel.vehicleCategory.file",
            "vehicleColor",
            "vehicleFuelType",
            "vehicleFuelType.file",
            "vehicleTransmission",
            "vehicleTransmission.file",
            "addresses",
            "addresses.address",
            "addresses.address.country",
            "addresses.address.city",
            "addresses.address.state"
    })
    Optional<CustomerVehicle> findById(UUID customerVehicleId);

    boolean existsByCode(String code);
}