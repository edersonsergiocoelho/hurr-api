package br.com.escconsulting.repository;

import br.com.escconsulting.entity.Address;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    @EntityGraph(attributePaths = {"city", "state", "country"})
    Optional<Address> findById(UUID addressId);
}