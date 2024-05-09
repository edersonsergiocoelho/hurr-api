package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.address.address.type.AddressAddressTypeSearchDTO;
import br.com.escconsulting.entity.AddressAddressType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressAddressTypeCustomRepository extends JpaRepository<AddressAddressType, UUID> {

    Page<AddressAddressType> searchPage(AddressAddressTypeSearchDTO addressAddressTypeSearchDTO, Pageable pageable);

    Long countSearchPage(AddressAddressTypeSearchDTO addressAddressTypeSearchDTO);
}