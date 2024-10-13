package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestDTO;
import br.com.escconsulting.dto.customer.vehicle.withdrawal.request.CustomerVehicleWithdrawalRequestSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleWithdrawalRequest;
import br.com.escconsulting.repository.CustomerVehicleWithdrawalRequestRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleWithdrawalRequestCustomRepository;
import br.com.escconsulting.service.CustomerWithdrawalRequestService;
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
public class CustomerWithdrawalRequestServiceImpl implements CustomerWithdrawalRequestService {

    // Service's
    private final EmailService emailService;

    private final PaymentStatusService paymentStatusService;

    // Repository's
    private final CustomerVehicleWithdrawalRequestRepository customerVehicleWithdrawalRequestRepository;

    private final CustomerVehicleWithdrawalRequestCustomRepository customerVehicleWithdrawalRequestCustomRepository;

    @Transactional
    @Override
    public Optional<CustomerVehicleWithdrawalRequest> findById(UUID customerWithdrawalRequestId) {

        return Optional.ofNullable(customerVehicleWithdrawalRequestRepository.findById(customerWithdrawalRequestId)
                .orElseThrow(() -> new RuntimeException("CustomerWithdrawalRequest not found with customerWithdrawalRequestId: " + customerWithdrawalRequestId)));
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

        customerVehicleWithdrawalRequests.forEach(customerWithdrawalRequest -> {

            customerWithdrawalRequest.setCreatedDate(Instant.now());
            customerWithdrawalRequest.setEnabled(Boolean.TRUE);
        });

        return customerVehicleWithdrawalRequestRepository.saveAll(customerVehicleWithdrawalRequests);
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleWithdrawalRequest> update(UUID customerWithdrawalRequestId, CustomerVehicleWithdrawalRequest customerVehicleWithdrawalRequest) {
        return findById(customerWithdrawalRequestId)
                .map(existingCustomerWithdrawalRequest -> {

                    existingCustomerWithdrawalRequest.setEnabled(customerVehicleWithdrawalRequest.getEnabled());
                    existingCustomerWithdrawalRequest.setModifiedDate(Instant.now());

                    return customerVehicleWithdrawalRequestRepository.save(existingCustomerWithdrawalRequest);
                });
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleWithdrawalRequest> approval(UUID customerWithdrawalRequestId) {
        return findById(customerWithdrawalRequestId)
                .map(existingCustomerWithdrawalRequest -> {

                    existingCustomerWithdrawalRequest.setPaymentStatus(paymentStatusService.findByPaymentStatusName("PAID").get());

                    existingCustomerWithdrawalRequest.setWithdrawalDate(LocalDateTime.now());
                    existingCustomerWithdrawalRequest.setModifiedDate(Instant.now());

                    return customerVehicleWithdrawalRequestRepository.save(existingCustomerWithdrawalRequest);
                }).map(existingCustomerWithdrawalRequest -> {
                    emailService.sendCustomerWithdrawalRequestApproval(existingCustomerWithdrawalRequest);
                    return existingCustomerWithdrawalRequest;
                });
    }

    @Transactional
    @Override
    public void delete(UUID customerWithdrawalRequestId) {
        findById(customerWithdrawalRequestId).ifPresent(customerVehicleWithdrawalRequestRepository::delete);
    }
}