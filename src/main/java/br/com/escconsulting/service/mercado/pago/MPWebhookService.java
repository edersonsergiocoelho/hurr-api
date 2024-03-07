package br.com.escconsulting.service.mercado.pago;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.springframework.http.ResponseEntity;

public interface MPWebhookService {

    ResponseEntity<String> webhook(String notification) throws JsonProcessingException, MPException, MPApiException;
}