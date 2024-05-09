package br.com.escconsulting.service;

import br.com.escconsulting.dto.address.address.type.AddressAddressTypeSearchDTO;
import br.com.escconsulting.entity.AddressAddressType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressAddressTypeService {

    Optional<AddressAddressType> findById(UUID addressAddressTypeId);

    List<AddressAddressType> findAll();

    Page<AddressAddressType> searchPage(AddressAddressTypeSearchDTO addressAddressTypeSearchDTO, Pageable pageable);

    Optional<AddressAddressType> save(AddressAddressType addressAddressType);

    Optional<AddressAddressType> update(UUID addressAddressTypeId, AddressAddressType addressAddressType);

    void delete(UUID addressAddressTypeId);
}