package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicleBankAccount;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerVehicleBankAccountRepository extends JpaRepository<CustomerVehicleBankAccount, UUID> {

    @EntityGraph(attributePaths = {
            "customer",
            "bank",
            "bank.file"
    })
    List<CustomerVehicleBankAccount> findAll();
}