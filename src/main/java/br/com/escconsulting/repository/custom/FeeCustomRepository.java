package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.fee.FeeDTO;
import br.com.escconsulting.dto.fee.FeeSearchDTO;
import br.com.escconsulting.entity.Fee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeeCustomRepository extends JpaRepository<Fee, UUID> {

    Page<FeeDTO> searchPage(FeeSearchDTO feeSearchDTO, Pageable pageable);

    Long countSearchPage(FeeSearchDTO feeSearchDTO);
}