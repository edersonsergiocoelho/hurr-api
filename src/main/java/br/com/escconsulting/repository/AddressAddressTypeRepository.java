package br.com.escconsulting.repository;

import br.com.escconsulting.entity.AddressAddressType;
import br.com.escconsulting.entity.AddressAddressTypeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressAddressTypeRepository extends JpaRepository<AddressAddressType, AddressAddressTypeId> {

    @Query("SELECT aat FROM AddressAddressType aat " +
            "JOIN FETCH aat.address a " +
            "JOIN FETCH a.country " +
            "JOIN FETCH a.city " +
            "JOIN FETCH a.state " +
            "JOIN FETCH a.addressTypes ats " +
            "WHERE aat.id.addressId = :addressId")
    List<AddressAddressType> findAllByAddressId(@Param("addressId") UUID addressId);

    @Modifying
    @Query("DELETE FROM AddressAddressType aat WHERE aat.id.addressId = :addressId")
    void deleteAllByAddressId(@Param("addressId") UUID addressId);
}