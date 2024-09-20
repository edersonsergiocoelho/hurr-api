package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.file.insurance.CustomerVehicleFileInsuranceDTO;
import br.com.escconsulting.dto.customer.vehicle.file.insurance.CustomerVehicleFileInsuranceSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleFileInsurance;
import br.com.escconsulting.repository.CustomerVehicleFileInsuranceRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleFileInsuranceCustomRepository;
import br.com.escconsulting.service.CustomerVehicleFileInsuranceService;
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
public class CustomerVehicleFileInsuranceServiceImpl implements CustomerVehicleFileInsuranceService {

    // Repository's
    private final CustomerVehicleFileInsuranceRepository customerVehicleFileInsuranceRepository;

    private final CustomerVehicleFileInsuranceCustomRepository customerVehicleFileInsuranceCustomRepository;

    @Transactional
    @Override
    public Optional<CustomerVehicleFileInsurance> findById(UUID customerVehicleFileInsuranceId) {

        return Optional.ofNullable(customerVehicleFileInsuranceRepository.findById(customerVehicleFileInsuranceId)
                .orElseThrow(() -> new RuntimeException("CustomerVehicleFileInsurance not found with customerVehicleFileInsuranceId: " + customerVehicleFileInsuranceId)));
    }

    @Transactional
    @Override
    public List<CustomerVehicleFileInsurance> findByCustomerVehicle(UUID customerVehicleId) {
        return customerVehicleFileInsuranceRepository.findByCustomerVehicle(customerVehicleId);
    }

    @Transactional
    @Override
    public List<CustomerVehicleFileInsurance> findAll() {
        return customerVehicleFileInsuranceRepository.findAll();
    }

    @Transactional
    @Override
    public Page<CustomerVehicleFileInsuranceDTO> searchPage(LocalUser localUser, CustomerVehicleFileInsuranceSearchDTO customerVehicleFileInsuranceSearchDTO, Pageable pageable) {
        return customerVehicleFileInsuranceCustomRepository.searchPage(customerVehicleFileInsuranceSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleFileInsurance> save(CustomerVehicleFileInsurance customerVehicleFileInsurance) {

        customerVehicleFileInsurance.setCreatedDate(Instant.now());
        customerVehicleFileInsurance.setEnabled(Boolean.TRUE);

        return Optional.of(customerVehicleFileInsuranceRepository.save(customerVehicleFileInsurance));
    }

    @Transactional
    @Override
    public List<CustomerVehicleFileInsurance> saveAll(List<CustomerVehicleFileInsurance> customerVehicleFileInsurances) {

        customerVehicleFileInsurances.forEach(customerVehicleFileInsurance -> {

            customerVehicleFileInsurance.setCreatedDate(Instant.now());
            customerVehicleFileInsurance.setEnabled(Boolean.TRUE);
        });

        return customerVehicleFileInsuranceRepository.saveAll(customerVehicleFileInsurances);
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleFileInsurance> update(UUID customerVehicleFileInsuranceId, CustomerVehicleFileInsurance customerVehicleFileInsurance) {
        return findById(customerVehicleFileInsuranceId)
                .map(existingCustomerVehicleFileInsurance -> {

                    existingCustomerVehicleFileInsurance.setEnabled(customerVehicleFileInsurance.getEnabled());
                    existingCustomerVehicleFileInsurance.setModifiedDate(Instant.now());

                    return customerVehicleFileInsuranceRepository.save(existingCustomerVehicleFileInsurance);
                });
    }

    @Transactional
    @Override
    public void delete(UUID customerVehicleFileInsuranceId) {
        findById(customerVehicleFileInsuranceId).ifPresent(customerVehicleFileInsuranceRepository::delete);
    }
}