package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.payment.method.PaymentMethodDTO;
import br.com.escconsulting.dto.payment.method.PaymentMethodSearchDTO;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.PaymentMethod;
import br.com.escconsulting.repository.PaymentMethodRepository;
import br.com.escconsulting.repository.custom.PaymentMethodCustomRepository;
import br.com.escconsulting.service.CustomerService;
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

    private final CustomerService customerService;

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
    public Page<PaymentMethodDTO> searchPage(LocalUser localUser, PaymentMethodSearchDTO paymentMethodSearchDTO, Pageable pageable) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            paymentMethodSearchDTO.setCustomerId(customer.getCustomerId());
            return paymentMethodCustomRepository.searchPage(paymentMethodSearchDTO, pageable);
        }).orElseThrow(() -> new RuntimeException("Customer not found for email: " + localUser.getUsername()));
    }

    @Transactional
    @Override
    public Optional<PaymentMethod> save(PaymentMethod paymentMethod) {

        paymentMethod.setCreatedDate(Instant.now());
        paymentMethod.setEnabled(Boolean.TRUE);

        return Optional.of(paymentMethodRepository.save(paymentMethod));
    }

    @Transactional
    @Override
    public Optional<PaymentMethod> update(UUID paymentMethodId, PaymentMethod paymentMethod) {
        return findById(paymentMethodId)
                .map(existingPaymentMethod -> {

                    existingPaymentMethod.setEnabled(paymentMethod.getEnabled());
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