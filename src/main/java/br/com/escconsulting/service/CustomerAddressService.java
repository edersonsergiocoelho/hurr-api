package br.com.escconsulting.service;

import br.com.escconsulting.dto.customer.address.CustomerAddressSaveAddressDTO;
import br.com.escconsulting.dto.customer.address.CustomerAddressSearchDTO;
import br.com.escconsulting.entity.CustomerAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerAddressService {

    Optional<CustomerAddress> findById(UUID customerAddressId);

    List<CustomerAddress> findByCustomerId(UUID customerId);

    List<CustomerAddress> findByCustomerIdAndAddressTypeName(UUID customerId, String addressTypeName);

    List<CustomerAddress> findAll();

    Page<CustomerAddress> searchPage(CustomerAddressSearchDTO customerAddressSearchDTO, Pageable pageable);

    Optional<CustomerAddress> save(CustomerAddress customerAddress);

    Optional<CustomerAddress> saveAddress(CustomerAddressSaveAddressDTO customerAddressSaveAddressDTO);

    Optional<CustomerAddress> update(UUID customerAddressId, CustomerAddress customerAddress);

    void delete(UUID customerAddressId);
}