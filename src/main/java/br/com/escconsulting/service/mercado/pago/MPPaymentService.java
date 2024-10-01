package br.com.escconsulting.service.mercado.pago;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.payment.PaymentRefund;

import java.math.BigDecimal;
import java.util.Optional;

public interface MPPaymentService {

    Optional<Payment> findById(Long paymentId) throws MPException, MPApiException;

    Optional<PaymentRefund> refund(Long paymentId) throws MPException, MPApiException;

    Optional<PaymentRefund> refund(Long paymentId, BigDecimal amount) throws MPException, MPApiException;
}