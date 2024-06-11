package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestDTO;
import br.com.escconsulting.dto.customer.withdrawal.request.CustomerWithdrawalRequestSearchDTO;
import br.com.escconsulting.entity.CustomerWithdrawalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerWithdrawalRequestCustomRepository extends JpaRepository<CustomerWithdrawalRequest, UUID> {

    Page<CustomerWithdrawalRequestDTO> searchPage(CustomerWithdrawalRequestSearchDTO customerWithdrawalRequestSearchDTO, Pageable pageable);

    Long countSearchPage(CustomerWithdrawalRequestSearchDTO customerWithdrawalRequestSearchDTO);
}