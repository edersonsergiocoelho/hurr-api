package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicleReview;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerVehicleReviewRepository extends JpaRepository<CustomerVehicleReview, UUID> {

    @EntityGraph(attributePaths = {"customer", "customerVehicleBooking"})
    Optional<CustomerVehicleReview> findById(UUID customerVehicleReviewId);

    @Query("SELECT cvr FROM CustomerVehicleReview cvr " +
            "JOIN FETCH cvr.customer c " +
            "JOIN FETCH cvr.customerVehicleBooking cvb " +
            "WHERE cvb.customerVehicleBookingId = :customerVehicleBookingId " +
            "AND c.customerId = :customerId")
    Optional<CustomerVehicleReview> findByCustomerVehicleBookingIdAndCustomerId(@Param("customerVehicleBookingId") UUID customerVehicleBookingId,
                                                                                @Param("customerId") UUID customerId);

    @Query("SELECT cvr FROM CustomerVehicleReview cvr " +
           "JOIN FETCH cvr.customer c " +
           "JOIN FETCH cvr.customerVehicleBooking cvb " +
           "JOIN FETCH cvb.customerVehicle cv " +
           "JOIN FETCH cv.customer cvc " +
           "WHERE cvc.customerId != c.customerId " +
           "AND cvb.customerVehicle.customerVehicleId = :customerVehicleId")
    List<CustomerVehicleReview> findAllByCustomerVehicleId(@Param("customerVehicleId") UUID customerVehicleId);
}