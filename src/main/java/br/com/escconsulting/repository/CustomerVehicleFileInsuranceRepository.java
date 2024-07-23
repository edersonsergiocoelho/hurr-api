package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicleFileInsurance;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerVehicleFileInsuranceRepository extends JpaRepository<CustomerVehicleFileInsurance, UUID> {

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
    Optional<CustomerVehicleFileInsurance> findById(UUID customerVehicleFileInsuranceId);

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
    @Query("SELECT cvfi FROM CustomerVehicleFileInsurance cvfi WHERE cvfi.customerVehicle.customerVehicleId = :customerVehicleId")
    List<CustomerVehicleFileInsurance> findByCustomerVehicle(@Param("customerVehicleId") UUID customerVehicleId);
}