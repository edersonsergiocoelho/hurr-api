package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerBankAccountRepository extends JpaRepository<CustomerBankAccount, UUID> {
    
}