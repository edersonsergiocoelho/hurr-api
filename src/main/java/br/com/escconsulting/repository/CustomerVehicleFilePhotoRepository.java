package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicleFilePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerVehicleFilePhotoRepository extends JpaRepository<CustomerVehicleFilePhoto, UUID> {

    Optional<CustomerVehicleFilePhoto> findById(UUID customerVehicleFilePhotoId);

    @Query("SELECT cvfp FROM CustomerVehicleFilePhoto cvfp " +
            "WHERE cvfp.customerVehicle.customerVehicleId = :customerVehicleId ")
    List<CustomerVehicleFilePhoto> findByCustomerVehicle(@Param("customerVehicleId") UUID customerVehicleId);

    @Query("SELECT cvfp FROM CustomerVehicleFilePhoto cvfp " +
           "WHERE cvfp.customerVehicle.customerVehicleId = :customerVehicleId " +
           "AND cvfp.coverPhoto = true")
    Optional<CustomerVehicleFilePhoto> findByCustomerVehicleAndCoverPhoto(@Param("customerVehicleId") UUID customerVehicleId);

    @Modifying
    @Query("DELETE FROM CustomerVehicleFilePhoto c WHERE c.customerVehicle.id = :customerVehicleId")
    void deleteByCustomerVehicleId(@Param("customerVehicleId") UUID customerVehicleId);
}