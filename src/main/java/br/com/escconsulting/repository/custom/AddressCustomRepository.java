package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.address.AddressSearchDTO;
import br.com.escconsulting.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressCustomRepository extends JpaRepository<Address, UUID> {

    Page<Address> searchPage(AddressSearchDTO addressSearchDTO, Pageable pageable);

    Long countSearchPage(AddressSearchDTO addressSearchDTO);
}