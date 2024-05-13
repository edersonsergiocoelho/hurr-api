package br.com.escconsulting.repository;

import br.com.escconsulting.entity.AddressAddressType;
import br.com.escconsulting.entity.AddressAddressTypeId;
import br.com.escconsulting.entity.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressAddressTypeRepository extends JpaRepository<AddressAddressType, AddressAddressTypeId> {

}