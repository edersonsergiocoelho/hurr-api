package br.com.escconsulting.repository;

import br.com.escconsulting.entity.VehicleTransmission;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleTransmissionRepository extends JpaRepository<VehicleTransmission, UUID> {

    @EntityGraph(attributePaths = {"file"})
    Optional<VehicleTransmission> findById(UUID vehicleTransmissionId);

    @EntityGraph(attributePaths = {"file"})
    List<VehicleTransmission> findAll();
}