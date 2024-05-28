package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.customer.address.CustomerAddressSaveAddressDTO;
import br.com.escconsulting.dto.customer.address.CustomerAddressSearchDTO;
import br.com.escconsulting.entity.*;
import br.com.escconsulting.repository.custom.CustomerAddressCustomRepository;
import br.com.escconsulting.repository.CustomerAddressRepository;
import br.com.escconsulting.security.jwt.TokenAuthenticationFilter;
import br.com.escconsulting.service.AddressAddressTypeService;
import br.com.escconsulting.service.AddressService;
import br.com.escconsulting.service.CustomerAddressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerAddressServiceImpl.class);

    private final AddressService addressService;

    private final AddressAddressTypeService addressAddressTypeService;

    private final CustomerAddressRepository customerAddressRepository;

    private final CustomerAddressCustomRepository customerAddressCustomRepository;

    @Transactional
    @Override
    public Optional<CustomerAddress> findById(UUID customerAddressId) {

        return Optional.ofNullable(customerAddressRepository.findById(customerAddressId)
                .orElseThrow(() -> new RuntimeException("Customer Address not found with customerAddressId: " + customerAddressId)));
    }

    @Transactional
    @Override
    public List<CustomerAddress> findByCustomerId(UUID customerId) {
        return customerAddressRepository.findByCustomerId(customerId);
    }

    @Transactional
    @Override
    public List<CustomerAddress> findByCustomerIdAndAddressTypeName(UUID customerId, String addressTypeName) {
        return customerAddressRepository.findByCustomerIdAndAddressTypeName(customerId, addressTypeName);
    }

    @Transactional
    @Override
    public List<CustomerAddress> findAll() {
        return customerAddressRepository.findAll();
    }

    @Transactional
    @Override
    public Page<CustomerAddress> searchPage(CustomerAddressSearchDTO fileApprovedSearchDTO, Pageable pageable) {
        return customerAddressCustomRepository.searchPage(fileApprovedSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<CustomerAddress> save(CustomerAddress fileApproved) {

        fileApproved.setCreatedDate(Instant.now());
        fileApproved.setEnabled(Boolean.TRUE);

        return Optional.of(customerAddressRepository.save(fileApproved));
    }

    @Transactional
    @Override
    public Optional<CustomerAddress> saveAddress(CustomerAddressSaveAddressDTO customerAddressSaveAddressDTO) {

        try {
            Optional<Address> optionalAddress = addressService.save(customerAddressSaveAddressDTO.getAddress());

            if (optionalAddress.isPresent()) {

                Address savedAddress = optionalAddress.get();

                for (AddressType addressType : customerAddressSaveAddressDTO.getAddressTypes()) {

                    AddressAddressType addressAddressType = new AddressAddressType();

                    addressAddressType.setAddress(savedAddress);
                    addressAddressType.setAddressType(addressType);

                    AddressAddressTypeId addressAddressTypeId = new AddressAddressTypeId();
                    addressAddressTypeId.setAddressId(savedAddress.getAddressId());
                    addressAddressTypeId.setAddressTypeId(addressType.getAddressTypeId());

                    addressAddressType.setId(addressAddressTypeId);

                    addressAddressTypeService.save(addressAddressType);
                }

                CustomerAddress customerAddress = new CustomerAddress();
                customerAddress.setAddress(savedAddress);
                customerAddress.setCustomer(customerAddressSaveAddressDTO.getCustomer());

                return this.save(customerAddress);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error("Erro ao salvar o endereço: " + e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<CustomerAddress> update(UUID customerAddressId, CustomerAddress fileApproved) {
        return findById(customerAddressId)
                .map(existingCustomerAddress -> {

                    existingCustomerAddress.setEnabled(fileApproved.getEnabled());
                    existingCustomerAddress.setModifiedDate(Instant.now());

                    return customerAddressRepository.save(existingCustomerAddress);

                }).map(savedCustomerAddress -> {

                    return savedCustomerAddress;
                });
    }

    @Transactional
    @Override
    public void delete(UUID customerAddressId) {
        findById(customerAddressId).ifPresent(customerAddressRepository::delete);
    }
}