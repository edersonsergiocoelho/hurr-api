package br.com.escconsulting.repository;

import br.com.escconsulting.entity.Bank;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<Bank, UUID> {

    @EntityGraph(attributePaths = {"file"})
    Optional<Bank> findById(UUID bankId);

    @EntityGraph(attributePaths = {"file"})
    List<Bank> findAll();
}