package br.com.escconsulting.service;

import br.com.escconsulting.dto.address.AddressSearchDTO;
import br.com.escconsulting.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressService {
    Optional<Address> findById(UUID addressId);

    List<Address> findAll();

    Page<Address> searchPage(AddressSearchDTO addressSearchDTO, Pageable pageable);

    Optional<Address> save(Address address);

    Optional<Address> update(UUID addressId, Address address);

    void delete(UUID addressId);
}