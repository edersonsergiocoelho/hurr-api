package br.com.escconsulting.repository;

import br.com.escconsulting.entity.VehicleBrand;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleBrandRepository extends JpaRepository<VehicleBrand, UUID> {

	@EntityGraph(attributePaths = {"file"})
	Optional<VehicleBrand> findById(UUID vehicleBrandId);

	@EntityGraph(attributePaths = {"file"})
	List<VehicleBrand> findAll();

	Optional<VehicleBrand> findByVehicleBrandName(String name);
}