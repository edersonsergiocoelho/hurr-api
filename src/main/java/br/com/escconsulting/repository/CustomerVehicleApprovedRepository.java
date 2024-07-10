package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicleApproved;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerVehicleApprovedRepository extends JpaRepository<CustomerVehicleApproved, UUID> {

    @EntityGraph(attributePaths = {
            "customer",
            "customerVehicleBooking",
            "customerBankAccount",
            "customerBankAccount.customer",
            "customerBankAccount.bank",
            "paymentMethod",
            "paymentStatus"
    })
    Optional<CustomerVehicleApproved> findById(UUID customerVehicleApprovedId);
}