package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.customer.address.CustomerAddressSearchDTO;
import br.com.escconsulting.entity.CustomerAddress;
import br.com.escconsulting.repository.custom.CustomerAddressCustomRepository;
import br.com.escconsulting.repository.CustomerAddressRepository;
import br.com.escconsulting.service.CustomerAddressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    private final CustomerAddressRepository fileApprovedRepository;

    private final CustomerAddressCustomRepository customerAddressCustomRepository;

    @Override
    public Optional<CustomerAddress> findById(UUID customerAddressId) {

        return Optional.ofNullable(fileApprovedRepository.findById(customerAddressId)
                .orElseThrow(() -> new RuntimeException("Customer Address not found with customerAddressId: " + customerAddressId)));
    }

    @Override
    public List<CustomerAddress> findByCustomerId(UUID customerId) {
        return fileApprovedRepository.findByCustomerId(customerId);
    }

    @Override
    public List<CustomerAddress> findAll() {
        return fileApprovedRepository.findAll();
    }

    @Override
    public Page<CustomerAddress> searchPage(CustomerAddressSearchDTO fileApprovedSearchDTO, Pageable pageable) {
        return customerAddressCustomRepository.searchPage(fileApprovedSearchDTO, pageable);
    }

    @Override
    public Optional<CustomerAddress> save(CustomerAddress fileApproved) {

        fileApproved.setCreatedDate(Instant.now());
        fileApproved.setEnabled(Boolean.TRUE);

        return Optional.of(fileApprovedRepository.save(fileApproved));
    }

    @Override
    @Transactional
    public Optional<CustomerAddress> update(UUID customerAddressId, CustomerAddress fileApproved) {
        return findById(customerAddressId)
                .map(existingCustomerAddress -> {

                    existingCustomerAddress.setEnabled(fileApproved.getEnabled());
                    existingCustomerAddress.setModifiedDate(Instant.now());

                    return fileApprovedRepository.save(existingCustomerAddress);

                }).map(savedCustomerAddress -> {

                    return savedCustomerAddress;
                });
    }

    @Override
    public void delete(UUID customerAddressId) {
        findById(customerAddressId).ifPresent(fileApprovedRepository::delete);
    }
}