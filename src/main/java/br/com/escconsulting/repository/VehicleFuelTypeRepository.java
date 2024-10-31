package br.com.escconsulting.repository;

import br.com.escconsulting.entity.VehicleFuelType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleFuelTypeRepository extends JpaRepository<VehicleFuelType, UUID> {

    @EntityGraph(attributePaths = {"file"})
    Optional<VehicleFuelType> findById(UUID vehicleFuelTypeId);

    @EntityGraph(attributePaths = {"file"})
    List<VehicleFuelType> findAll();
}