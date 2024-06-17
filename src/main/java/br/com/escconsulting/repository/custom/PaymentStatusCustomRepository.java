package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.payment.status.PaymentStatusDTO;
import br.com.escconsulting.dto.payment.status.PaymentStatusSearchDTO;
import br.com.escconsulting.entity.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentStatusCustomRepository extends JpaRepository<PaymentStatus, UUID> {

    Page<PaymentStatusDTO> searchPage(PaymentStatusSearchDTO paymentStatusSearchDTO, Pageable pageable);

    Long countSearchPage(PaymentStatusSearchDTO paymentStatusSearchDTO);
}