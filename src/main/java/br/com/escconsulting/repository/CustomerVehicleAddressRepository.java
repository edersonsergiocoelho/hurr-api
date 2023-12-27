package br.com.escconsulting.repository;

import br.com.escconsulting.entity.CustomerVehicleAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerVehicleAddressRepository extends JpaRepository<CustomerVehicleAddress, UUID> {

    @Query("SELECT cva FROM CustomerVehicleAddress cva " +
            "JOIN FETCH cva.customerVehicle cv " +
            "JOIN FETCH cva.address address " +
            "JOIN FETCH address.country " +
            "JOIN FETCH address.city " +
            "JOIN FETCH address.state " +
            "WHERE cv.customerVehicleId = :customerVehicleId")
    List<CustomerVehicleAddress> findAllByCustomerVehicleId(@Param("customerVehicleId") UUID customerVehicleId);

    @Query("SELECT cva FROM CustomerVehicleAddress cva " +
            "JOIN FETCH cva.customerVehicle cv " +
            "JOIN FETCH cva.address address " +
            "JOIN FETCH address.country " +
            "JOIN FETCH address.city " +
            "JOIN FETCH address.state " +
            "WHERE cv.customerVehicleId = :customerVehicleId " +
            "AND CAST(cva.addressType AS text) = :addressType")
    List<CustomerVehicleAddress> findAllByCustomerVehicleIdAndAddressType(@Param("customerVehicleId") UUID customerVehicleId, @Param("addressType") String addressType);
}