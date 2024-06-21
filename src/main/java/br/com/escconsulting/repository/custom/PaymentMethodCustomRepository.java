package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.payment.method.PaymentMethodDTO;
import br.com.escconsulting.dto.payment.method.PaymentMethodSearchDTO;
import br.com.escconsulting.entity.PaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentMethodCustomRepository extends JpaRepository<PaymentMethod, UUID> {

    Page<PaymentMethodDTO> searchPage(PaymentMethodSearchDTO paymentMethodSearchDTO, Pageable pageable);

    Long countSearchPage(PaymentMethodSearchDTO paymentMethodSearchDTO);
}