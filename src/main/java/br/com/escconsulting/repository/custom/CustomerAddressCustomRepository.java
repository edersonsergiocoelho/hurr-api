package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.customer.address.CustomerAddressSearchDTO;
import br.com.escconsulting.entity.CustomerAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerAddressCustomRepository extends JpaRepository<CustomerAddress, UUID> {

    Page<CustomerAddress> searchPage(CustomerAddressSearchDTO customerAddressSearchDTO, Pageable pageable);

    Long countSearchPage(CustomerAddressSearchDTO customerAddressSearchDTO);
}