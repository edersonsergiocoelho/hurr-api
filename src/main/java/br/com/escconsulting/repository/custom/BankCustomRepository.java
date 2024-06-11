package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.bank.BankDTO;
import br.com.escconsulting.dto.bank.BankSearchDTO;
import br.com.escconsulting.entity.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankCustomRepository extends JpaRepository<Bank, UUID> {

    Page<BankDTO> searchPage(BankSearchDTO bankSearchDTO, Pageable pageable);

    Long countSearchPage(BankSearchDTO bankSearchDTO);
}