package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerBankAccount;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerBankAccountRepository extends JpaRepository<CustomerBankAccount, UUID> {

    @EntityGraph(attributePaths = {"customer", "bank"})
    List<CustomerBankAccount> findAll();
}