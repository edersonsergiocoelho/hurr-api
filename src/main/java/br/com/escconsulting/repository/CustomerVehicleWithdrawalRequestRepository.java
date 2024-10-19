package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicleWithdrawalRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerVehicleWithdrawalRequestRepository extends JpaRepository<CustomerVehicleWithdrawalRequest, UUID> {

    @EntityGraph(attributePaths = {
            "customer",
            "customerVehicleBooking",
            "customerVehicleBankAccount",
            "customerVehicleBankAccount.customer",
            "customerVehicleBankAccount.bank",
            "paymentMethod",
            "paymentStatus"
    })
    Optional<CustomerVehicleWithdrawalRequest> findById(UUID customerVehicleWithdrawalRequestId);
}