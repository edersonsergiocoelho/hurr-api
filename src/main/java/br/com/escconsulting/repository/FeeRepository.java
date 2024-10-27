package br.com.escconsulting.repository;

import br.com.escconsulting.entity.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeeRepository extends JpaRepository<Fee, UUID> {

    Optional<Fee> findById(UUID feeId);

    List<Fee> findAll();
}