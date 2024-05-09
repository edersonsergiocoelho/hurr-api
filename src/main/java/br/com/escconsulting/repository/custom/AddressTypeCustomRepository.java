package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.address.type.AddressTypeSearchDTO;
import br.com.escconsulting.entity.AddressType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressTypeCustomRepository extends JpaRepository<AddressType, UUID> {

    Page<AddressType> searchPage(AddressTypeSearchDTO addressTypeSearchDTO, Pageable pageable);

    Long countSearchPage(AddressTypeSearchDTO addressTypeSearchDTO);
}