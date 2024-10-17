package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestDTO;
import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleWithdrawalRequest;
import br.com.escconsulting.repository.CustomerVehicleWithdrawalRequestRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleWithdrawalRequestCustomRepository;
import br.com.escconsulting.service.CustomerVehicleWithdrawalRequestService;
import br.com.escconsulting.service.EmailService;
import br.com.escconsulting.service.PaymentStatusService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleWithdrawalRequestServiceImpl implements CustomerVehicleWithdrawalRequestService {

    // Service's
    private final EmailService emailService;

    private final PaymentStatusService paymentStatusService;

    // Repository's
    private final CustomerVehicleWithdrawalRequestRepository customerVehicleWithdrawalRequestRepository;

    private final CustomerVehicleWithdrawalRequestCustomRepository customerVehicleWithdrawalRequestCustomRepository;

    @Transactional
    @Override
    public Optional<CustomerVehicleWithdrawalRequest> findById(UUID customerVehicleWithdrawalRequestId) {

        return Optional.ofNullable(customerVehicleWithdrawalRequestRepository.findById(customerVehicleWithdrawalRequestId)
                .orElseThrow(() -> new RuntimeException("CustomerVehicleWithdrawalRequest not found with customerVehicleWithdrawalRequestId: " + customerVehicleWithdrawalRequestId)));
    }

    @Transactional
    @Override
    public List<CustomerVehicleWithdrawalRequest> findAll() {
        return customerVehicleWithdrawalRequestRepository.findAll();
    }

    @Transactional
    @Override
    public Page<CustomerVehicleWithdrawalRequestDTO> searchPage(LocalUser localUser, CustomerVehicleWithdrawalRequestSearchDTO customerVehicleWithdrawalRequestSearchDTO, Pageable pageable) {
        return customerVehicleWithdrawalRequestCustomRepository.searchPage(customerVehicleWithdrawalRequestSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleWithdrawalRequest> save(CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest) {

        customerVehicleWithdrawalRequest.setCreatedDate(Instant.now());
        customerVehicleWithdrawalRequest.setEnabled(Boolean.TRUE);

        return Optional.of(customerVehicleWithdrawalRequestRepository.save(customerVehicleWithdrawalRequest));
    }

    @Transactional
    @Override
    public List<CustomerVehicleWithdrawalRequest> saveAll(List<CustomerVehicleWithdrawalRequest> customerVehicleWithdrawalRequests) {

        customerVehicleWithdrawalRequests.forEach(customerVehicleWithdrawalRequest -> {

            customerVehicleWithdrawalRequest.setCreatedDate(Instant.now());
            customerVehicleWithdrawalRequest.setEnabled(Boolean.TRUE);
        });

        return customerVehicleWithdrawalRequestRepository.saveAll(customerVehicleWithdrawalRequests);
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleWithdrawalRequest> update(UUID customerVehicleWithdrawalRequestId, CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest) {
        return findById(customerVehicleWithdrawalRequestId)
                .map(existingCustomerVehicleWithdrawalRequest -> {

                    existingCustomerVehicleWithdrawalRequest.setEnabled(customerVehicleWithdrawalRequest.getEnabled());
                    existingCustomerVehicleWithdrawalRequest.setModifiedDate(Instant.now());

                    return customerVehicleWithdrawalRequestRepository.save(existingCustomerVehicleWithdrawalRequest);
                });
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleWithdrawalRequest> approval(UUID customerVehicleWithdrawalRequestId) {
        return findById(customerVehicleWithdrawalRequestId)
                .map(existingCustomerVehicleWithdrawalRequest -> {

                    existingCustomerVehicleWithdrawalRequest.setPaymentStatus(paymentStatusService.findByPaymentStatusName("PAID").get());

                    existingCustomerVehicleWithdrawalRequest.setWithdrawalDate(LocalDateTime.now());
                    existingCustomerVehicleWithdrawalRequest.setModifiedDate(Instant.now());

                    return customerVehicleWithdrawalRequestRepository.save(existingCustomerVehicleWithdrawalRequest);
                }).map(existingCustomerVehicleWithdrawalRequest -> {
                    emailService.sendCustomerVehicleWithdrawalRequestApproval(existingCustomerVehicleWithdrawalRequest);
                    return existingCustomerVehicleWithdrawalRequest;
                });
    }

    @Transactional
    @Override
    public void delete(UUID customerVehicleWithdrawalRequestId) {
        findById(customerVehicleWithdrawalRequestId).ifPresent(customerVehicleWithdrawalRequestRepository::delete);
    }
}