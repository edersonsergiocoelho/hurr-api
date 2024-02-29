package br.com.escconsulting.service.mercado.pago;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPResourceList;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.paymentmethod.PaymentMethod;

public interface MPPaymentCreateService {

    Payment create() throws MPException, MPApiException;

    Payment createTest() throws MPException, MPApiException;

    Payment createTest2() throws MPException, MPApiException;

    MPResourceList<PaymentMethod> findAll() throws MPException, MPApiException;
}