package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.payment.method.PaymentMethodDTO;
import br.com.escconsulting.dto.payment.method.PaymentMethodSearchDTO;
import br.com.escconsulting.entity.File;
import br.com.escconsulting.entity.PaymentMethod;
import br.com.escconsulting.mapper.PaymentMethodMapper;
import br.com.escconsulting.repository.PaymentMethodRepository;
import br.com.escconsulting.repository.custom.PaymentMethodCustomRepository;
import br.com.escconsulting.service.FileService;
import br.com.escconsulting.service.PaymentMethodService;
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
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final FileService fileService;

    private final PaymentMethodRepository paymentMethodRepository;

    private final PaymentMethodCustomRepository paymentMethodCustomRepository;

    @Transactional
    @Override
    public Optional<PaymentMethod> findById(UUID paymentMethodId) {

        return Optional.ofNullable(paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new RuntimeException("PaymentMethod not found with paymentMethodId: " + paymentMethodId)));
    }

    @Transactional
    @Override
    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }

    @Transactional
    @Override
    public Page<PaymentMethodDTO> searchPage(PaymentMethodSearchDTO paymentMethodSearchDTO, Pageable pageable) {
        return paymentMethodCustomRepository.searchPage(paymentMethodSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<PaymentMethod> save(PaymentMethod paymentMethod) {

        if (paymentMethod.getFile() != null) {

            Optional<File> optionalFile = fileService.save(paymentMethod.getFile());

            optionalFile.ifPresent(paymentMethod::setFile);
        }

        return Optional.of(paymentMethodRepository.save(paymentMethod));
    }

    @Transactional
    @Override
    public Optional<PaymentMethod> update(UUID paymentMethodId, PaymentMethod paymentMethod) {
        return findById(paymentMethodId)
                .map(existingPaymentMethod -> {

                    if (paymentMethod.getFile() != null) {

                        if (existingPaymentMethod.getFile() != null && existingPaymentMethod.getFile().getFileId() != null) {

                            if (paymentMethod.getFile().getFileId() == null && existingPaymentMethod.getFile().getFileId() != null) {

                                fileService.delete(existingPaymentMethod.getFile().getFileId());
                            }
                        }

                        Optional<File> optionalFile = fileService.save(paymentMethod.getFile());

                        optionalFile.ifPresent(existingPaymentMethod::setFile);
                    }

                    PaymentMethodMapper.INSTANCE.update(paymentMethod, existingPaymentMethod);

                    existingPaymentMethod.setModifiedDate(Instant.now());

                    return paymentMethodRepository.save(existingPaymentMethod);
                });
    }

    @Transactional
    @Override
    public void delete(UUID paymentMethodId) {
        findById(paymentMethodId).ifPresent(paymentMethodRepository::delete);
    }
}