package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.payment.status.PaymentStatusDTO;
import br.com.escconsulting.dto.payment.status.PaymentStatusSearchDTO;
import br.com.escconsulting.entity.PaymentStatus;
import br.com.escconsulting.mapper.PaymentStatusMapper;
import br.com.escconsulting.repository.PaymentStatusRepository;
import br.com.escconsulting.repository.custom.PaymentStatusCustomRepository;
import br.com.escconsulting.service.PaymentStatusService;
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
public class PaymentStatusServiceImpl implements PaymentStatusService {

    private final PaymentStatusRepository paymentStatusRepository;

    private final PaymentStatusCustomRepository paymentStatusCustomRepository;

    @Transactional
    @Override
    public Optional<PaymentStatus> findById(UUID paymentStatusId) {

        return Optional.ofNullable(paymentStatusRepository.findById(paymentStatusId)
                .orElseThrow(() -> new RuntimeException("PaymentStatus not found with paymentStatusId: " + paymentStatusId)));
    }

    @Transactional
    @Override
    public Optional<PaymentStatus> findByPaymentStatusName(String paymentStatusName) {

        return Optional.ofNullable(paymentStatusRepository.findByPaymentStatusName(paymentStatusName)
                .orElseThrow(() -> new RuntimeException("PaymentStatus not found with paymentStatusName: " + paymentStatusName)));
    }

    @Transactional
    @Override
    public List<PaymentStatus> findAll() {
        return paymentStatusRepository.findAll();
    }

    @Transactional
    @Override
    public Page<PaymentStatusDTO> searchPage(PaymentStatusSearchDTO paymentStatusSearchDTO, Pageable pageable) {
        return paymentStatusCustomRepository.searchPage(paymentStatusSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<PaymentStatus> save(PaymentStatus paymentStatus) {
        return Optional.of(paymentStatusRepository.save(paymentStatus));
    }

    @Transactional
    @Override
    public Optional<PaymentStatus> update(UUID paymentStatusId, PaymentStatus paymentStatus) {
        return findById(paymentStatusId)
                .map(existingPaymentStatus -> {

                    PaymentStatusMapper.INSTANCE.update(paymentStatus, existingPaymentStatus);

                    existingPaymentStatus.setModifiedDate(Instant.now());

                    return paymentStatusRepository.save(existingPaymentStatus);
                });
    }

    @Transactional
    @Override
    public void delete(UUID paymentStatusId) {
        findById(paymentStatusId).ifPresent(paymentStatusRepository::delete);
    }

    @Transactional
    @Override
    public void deleteAll(List<UUID> paymentStatusIds) {
        paymentStatusRepository.deleteAllById(paymentStatusIds);
    }
}