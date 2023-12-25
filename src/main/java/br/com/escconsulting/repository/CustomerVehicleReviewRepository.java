package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicleReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerVehicleReviewRepository extends JpaRepository<CustomerVehicleReview, UUID> {

    @Query("SELECT cvr FROM CustomerVehicleReview cvr JOIN FETCH cvr.customer c WHERE cvr.customerVehicleId = :customerVehicleId")
    List<CustomerVehicleReview> findAllByCustomerVehicleId(@Param("customerVehicleId") UUID customerVehicleId);
}