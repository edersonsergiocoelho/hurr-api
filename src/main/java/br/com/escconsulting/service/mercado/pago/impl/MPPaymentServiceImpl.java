package br.com.escconsulting.service.mercado.pago.impl;

import br.com.escconsulting.config.MPConfig;
import br.com.escconsulting.service.mercado.pago.MPPaymentService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.payment.PaymentRefund;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MPPaymentServiceImpl implements MPPaymentService {

    private final MPConfig mpConfig;

    @Transactional
    @Override
    public Optional<Payment> findById(Long paymentId) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(mpConfig.getAccessToken());

        PaymentClient paymentClient = new PaymentClient();
        return Optional.ofNullable(paymentClient.get(paymentId));
    }

    @Transactional
    @Override
    public Optional<PaymentRefund> refund(Long paymentId) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(mpConfig.getAccessToken());

        PaymentClient paymentClient = new PaymentClient();
        return Optional.ofNullable(paymentClient.refund(paymentId));
    }

    @Transactional
    @Override
    public Optional<PaymentRefund> refund(Long paymentId, BigDecimal amount) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(mpConfig.getAccessToken());

        PaymentClient paymentClient = new PaymentClient();
        return Optional.ofNullable(paymentClient.refund(paymentId, amount));
    }
}