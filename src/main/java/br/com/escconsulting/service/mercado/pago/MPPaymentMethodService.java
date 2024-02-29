package br.com.escconsulting.service.mercado.pago;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPResourceList;
import com.mercadopago.resources.paymentmethod.PaymentMethod;

public interface MPPaymentMethodService {
    MPResourceList<PaymentMethod> findAll() throws MPException, MPApiException;
}