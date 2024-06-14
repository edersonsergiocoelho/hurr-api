package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.payment.method.PaymentMethodDTO;
import br.com.escconsulting.dto.payment.method.PaymentMethodSearchDTO;
import br.com.escconsulting.entity.PaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentMethodService {

    Optional<PaymentMethod> findById(UUID paymentMethodId);

    List<PaymentMethod> findAll();

    Page<PaymentMethodDTO> searchPage(LocalUser localUser, PaymentMethodSearchDTO paymentMethodSearchDTO, Pageable pageable);

    Optional<PaymentMethod> save(PaymentMethod paymentMethod);

    Optional<PaymentMethod> update(UUID paymentMethodId, PaymentMethod paymentMethod);

    void delete(UUID paymentMethodId);
}