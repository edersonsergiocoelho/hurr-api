package br.com.escconsulting.service;

import br.com.escconsulting.dto.address.type.AddressTypeSearchDTO;
import br.com.escconsulting.entity.AddressType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressTypeService {

    Optional<AddressType> findById(UUID addressTypeId);

    List<AddressType> findAll();

    Page<AddressType> searchPage(AddressTypeSearchDTO addressTypeSearchDTO, Pageable pageable);

    Optional<AddressType> save(AddressType addressType);

    Optional<AddressType> update(UUID addressTypeId, AddressType addressType);

    void delete(UUID addressTypeId);
}