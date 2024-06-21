package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestDTO;
import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestSearchDTO;
import br.com.escconsulting.entity.CustomerWithdrawalRequest;
import br.com.escconsulting.repository.CustomerWithdrawalRequestRepository;
import br.com.escconsulting.repository.custom.CustomerWithdrawalRequestCustomRepository;
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
    private final CustomerWithdrawalRequestRepository customerWithdrawalRequestRepository;

    private final CustomerWithdrawalRequestCustomRepository customerWithdrawalRequestCustomRepository;

    @Transactional
    @Override
    public Optional<CustomerWithdrawalRequest> findById(UUID customerWithdrawalRequestId) {

        return Optional.ofNullable(customerWithdrawalRequestRepository.findById(customerWithdrawalRequestId)
                .orElseThrow(() -> new RuntimeException("CustomerWithdrawalRequest not found with customerWithdrawalRequestId: " + customerWithdrawalRequestId)));
    }

    @Transactional
    @Override
    public List<CustomerWithdrawalRequest> findAll() {
        return customerWithdrawalRequestRepository.findAll();
    }

    @Transactional
    @Override
    public Page<CustomerWithdrawalRequestDTO> searchPage(LocalUser localUser, CustomerWithdrawalRequestSearchDTO customerWithdrawalRequestSearchDTO, Pageable pageable) {
        return customerWithdrawalRequestCustomRepository.searchPage(customerWithdrawalRequestSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<CustomerWithdrawalRequest> save(CustomerWithdrawalRequest customerWithdrawalRequest) {

        customerWithdrawalRequest.setCreatedDate(Instant.now());
        customerWithdrawalRequest.setEnabled(Boolean.TRUE);

        return Optional.of(customerWithdrawalRequestRepository.save(customerWithdrawalRequest));
    }

    @Transactional
    @Override
    public List<CustomerWithdrawalRequest> saveAll(List<CustomerWithdrawalRequest> customerWithdrawalRequests) {

        customerWithdrawalRequests.forEach(customerWithdrawalRequest -> {

            customerWithdrawalRequest.setCreatedDate(Instant.now());
            customerWithdrawalRequest.setEnabled(Boolean.TRUE);
        });

        return customerWithdrawalRequestRepository.saveAll(customerWithdrawalRequests);
    }

    @Transactional
    @Override
    public Optional<CustomerWithdrawalRequest> update(UUID customerWithdrawalRequestId, CustomerWithdrawalRequest customerWithdrawalRequest) {
        return findById(customerWithdrawalRequestId)
                .map(existingCustomerWithdrawalRequest -> {

                    existingCustomerWithdrawalRequest.setEnabled(customerWithdrawalRequest.getEnabled());
                    existingCustomerWithdrawalRequest.setModifiedDate(Instant.now());

                    return customerWithdrawalRequestRepository.save(existingCustomerWithdrawalRequest);
                });
    }

    @Transactional
    @Override
    public Optional<CustomerWithdrawalRequest> approval(UUID customerWithdrawalRequestId) {
        return findById(customerWithdrawalRequestId)
                .map(existingCustomerWithdrawalRequest -> {

                    existingCustomerWithdrawalRequest.setPaymentStatus(paymentStatusService.findByPaymentStatusName("PAID").get());

                    existingCustomerWithdrawalRequest.setWithdrawalDate(LocalDateTime.now());
                    existingCustomerWithdrawalRequest.setModifiedDate(Instant.now());

                    return customerWithdrawalRequestRepository.save(existingCustomerWithdrawalRequest);
                }).map(existingCustomerWithdrawalRequest -> {
                    emailService.sendCustomerWithdrawalRequestApproval(existingCustomerWithdrawalRequest);
                    return existingCustomerWithdrawalRequest;
                });
    }

    @Transactional
    @Override
    public void delete(UUID customerWithdrawalRequestId) {
        findById(customerWithdrawalRequestId).ifPresent(customerWithdrawalRequestRepository::delete);
    }
}