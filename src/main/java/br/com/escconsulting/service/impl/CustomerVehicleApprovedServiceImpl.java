package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedDTO;
import br.com.escconsulting.dto.customer.vehicle.approved.CustomerVehicleApprovedSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleApproved;
import br.com.escconsulting.mapper.CustomerVehicleApprovedMapper;
import br.com.escconsulting.repository.CustomerVehicleApprovedRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleApprovedCustomRepository;
import br.com.escconsulting.service.CustomerVehicleApprovedService;
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
public class CustomerVehicleApprovedServiceImpl implements CustomerVehicleApprovedService {

    // Repository's
    private final CustomerVehicleApprovedRepository customerVehicleApprovedRepository;

    private final CustomerVehicleApprovedCustomRepository customerVehicleApprovedCustomRepository;

    @Transactional
    @Override
    public Optional<CustomerVehicleApproved> findById(UUID customerVehicleApprovedId) {

        return Optional.ofNullable(customerVehicleApprovedRepository.findById(customerVehicleApprovedId)
                .orElseThrow(() -> new RuntimeException("CustomerVehicleApproved not found with customerVehicleApprovedId: " + customerVehicleApprovedId)));
    }

    @Transactional
    @Override
    public List<CustomerVehicleApproved> findAll() {
        return customerVehicleApprovedRepository.findAll();
    }

    @Transactional
    @Override
    public Page<CustomerVehicleApprovedDTO> searchPage(LocalUser localUser, CustomerVehicleApprovedSearchDTO customerVehicleApprovedSearchDTO, Pageable pageable) {
        return customerVehicleApprovedCustomRepository.searchPage(customerVehicleApprovedSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleApproved> save(CustomerVehicleApproved customerVehicleApproved) {

        customerVehicleApproved.setCreatedDate(Instant.now());
        customerVehicleApproved.setEnabled(Boolean.TRUE);

        return Optional.of(customerVehicleApprovedRepository.save(customerVehicleApproved));
    }

    @Transactional
    @Override
    public List<CustomerVehicleApproved> saveAll(List<CustomerVehicleApproved> customerVehicleApproveds) {

        customerVehicleApproveds.forEach(customerVehicleApproved -> {

            customerVehicleApproved.setCreatedDate(Instant.now());
            customerVehicleApproved.setEnabled(Boolean.TRUE);
        });

        return customerVehicleApprovedRepository.saveAll(customerVehicleApproveds);
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleApproved> update(UUID customerVehicleApprovedId, CustomerVehicleApproved customerVehicleApproved) {
        return findById(customerVehicleApprovedId)
                .map(existingCustomerVehicleApproved -> {

                    CustomerVehicleApprovedMapper.INSTANCE.update(customerVehicleApproved, existingCustomerVehicleApproved);

                    existingCustomerVehicleApproved.setModifiedDate(Instant.now());

                    return customerVehicleApprovedRepository.save(existingCustomerVehicleApproved);
                });
    }

    @Transactional
    @Override
    public void delete(UUID customerVehicleApprovedId) {
        findById(customerVehicleApprovedId).ifPresent(customerVehicleApprovedRepository::delete);
    }
}