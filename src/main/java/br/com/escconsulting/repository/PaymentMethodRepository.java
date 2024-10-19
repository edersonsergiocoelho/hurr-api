package br.com.escconsulting.repository;

import br.com.escconsulting.entity.PaymentMethod;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID> {

    @EntityGraph(attributePaths = {"file"})
    Optional<PaymentMethod> findById(UUID paymentMethodId);

    @EntityGraph(attributePaths = {"file"})
    List<PaymentMethod> findAll();
}