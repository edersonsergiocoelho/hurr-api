package br.com.escconsulting.repository;

import br.com.escconsulting.entity.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, UUID> {

    Optional<VehicleModel> findByVehicleModelName(String vehicleModelName);

    @Query("SELECT vehicleModel FROM VehicleModel vehicleModel JOIN FETCH vehicleModel.vehicle JOIN FETCH vehicleModel.vehicleCategory JOIN FETCH vehicle.vehicleBrand WHERE vehicleModel.vehicle.id = :vehicleId")
    List<VehicleModel> findByVehicleVehicleId(UUID vehicleId);
}