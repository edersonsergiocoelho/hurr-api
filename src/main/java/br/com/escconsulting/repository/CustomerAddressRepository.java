package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, UUID> {

    @Query("SELECT ca FROM CustomerAddress ca " +
            "INNER JOIN FETCH ca.customer c " +
            "INNER JOIN FETCH ca.address a " +
            "INNER JOIN FETCH a.country co " +
            "INNER JOIN FETCH a.state s " +
            "INNER JOIN FETCH a.city ci " +
            "WHERE c.customerId = :customerId")
    List<CustomerAddress> findByCustomerId(@Param("customerId") UUID customerId);
}