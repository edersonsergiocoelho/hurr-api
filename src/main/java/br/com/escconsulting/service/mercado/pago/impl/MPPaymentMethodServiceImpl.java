package br.com.escconsulting.service.mercado.pago.impl;

import br.com.escconsulting.config.MPConfig;
import br.com.escconsulting.service.mercado.pago.MPPaymentMethodService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.paymentmethod.PaymentMethodClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPResourceList;
import com.mercadopago.resources.paymentmethod.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MPPaymentMethodServiceImpl implements MPPaymentMethodService {

    private final MPConfig mpConfig;

    @Override
    public MPResourceList<PaymentMethod> findAll() throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken(mpConfig.getAccessToken());

        PaymentMethodClient client = new PaymentMethodClient();
        return client.list();
    }
}