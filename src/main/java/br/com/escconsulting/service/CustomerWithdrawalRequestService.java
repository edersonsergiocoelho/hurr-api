package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestDTO;
import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestSearchDTO;
import br.com.escconsulting.entity.CustomerWithdrawalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerWithdrawalRequestService {

    Optional<CustomerWithdrawalRequest> findById(UUID customerWithdrawalRequestId);

    List<CustomerWithdrawalRequest> findAll();

    Page<CustomerWithdrawalRequestDTO> searchPage(LocalUser localUser, CustomerWithdrawalRequestSearchDTO customerWithdrawalRequestSearchDTO, Pageable pageable);

    Optional<CustomerWithdrawalRequest> save(CustomerWithdrawalRequest customerWithdrawalRequest);

    Optional<CustomerWithdrawalRequest> update(UUID customerWithdrawalRequestId, CustomerWithdrawalRequest customerWithdrawalRequest);

    void delete(UUID customerWithdrawalRequestId);
}