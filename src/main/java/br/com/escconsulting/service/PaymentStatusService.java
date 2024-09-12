package br.com.escconsulting.service;

import br.com.escconsulting.dto.payment.status.PaymentStatusDTO;
import br.com.escconsulting.dto.payment.status.PaymentStatusSearchDTO;
import br.com.escconsulting.entity.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentStatusService {

    Optional<PaymentStatus> findById(UUID paymentStatusId);

    Optional<PaymentStatus> findByPaymentStatusName(String paymentStatusName);

    List<PaymentStatus> findAll();

    Page<PaymentStatusDTO> searchPage(PaymentStatusSearchDTO paymentStatusSearchDTO, Pageable pageable);

    Optional<PaymentStatus> save(PaymentStatus paymentStatus);

    Optional<PaymentStatus> update(UUID paymentStatusId, PaymentStatus paymentStatus);

    void delete(UUID paymentStatusId);

    void deleteAll(List<UUID> paymentStatusIds);
}