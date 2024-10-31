package br.com.escconsulting.repository;

import br.com.escconsulting.entity.Bank;
import br.com.escconsulting.entity.VehicleCategory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory, UUID> {

    @EntityGraph(attributePaths = {"file"})
    Optional<VehicleCategory> findById(UUID vehicleCategoryId);

    @EntityGraph(attributePaths = {"file"})
    List<VehicleCategory> findAll();
}