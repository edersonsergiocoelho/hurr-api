package br.com.escconsulting.repository;

import br.com.escconsulting.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

	@Query("SELECT v FROM Vehicle v JOIN FETCH v.vehicleBrand b WHERE b.vehicleBrandId = :vehicleBrandId")
	List<Vehicle> findByVehicleBrandVehicleBrandId(@Param("vehicleBrandId") UUID brandId);
}