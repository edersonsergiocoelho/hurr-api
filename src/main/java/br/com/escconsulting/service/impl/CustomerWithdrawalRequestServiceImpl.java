package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestDTO;
import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestSearchDTO;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.CustomerWithdrawalRequest;
import br.com.escconsulting.repository.CustomerWithdrawalRequestRepository;
import br.com.escconsulting.repository.custom.CustomerWithdrawalRequestCustomRepository;
import br.com.escconsulting.service.CustomerService;
import br.com.escconsulting.service.CustomerWithdrawalRequestService;
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
public class CustomerWithdrawalRequestServiceImpl implements CustomerWithdrawalRequestService {

    private final CustomerService customerService;

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
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            customerWithdrawalRequestSearchDTO.setCustomerId(customer.getCustomerId());
            return customerWithdrawalRequestCustomRepository.searchPage(customerWithdrawalRequestSearchDTO, pageable);
        }).orElseThrow(() -> new RuntimeException("Customer not found for email: " + localUser.getUsername()));
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
    public void delete(UUID customerWithdrawalRequestId) {
        findById(customerWithdrawalRequestId).ifPresent(customerWithdrawalRequestRepository::delete);
    }
}