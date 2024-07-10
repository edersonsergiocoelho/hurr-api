package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicleFileInsurance;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerVehicleFileInsuranceRepository extends JpaRepository<CustomerVehicleFileInsurance, UUID> {

    @EntityGraph(attributePaths = {
            "customer",
            "customerVehicleBooking",
            "customerBankAccount",
            "customerBankAccount.customer",
            "customerBankAccount.bank",
            "paymentMethod",
            "paymentStatus"
    })
    Optional<CustomerVehicleFileInsurance> findById(UUID customerVehicleFileInsuranceId);
}