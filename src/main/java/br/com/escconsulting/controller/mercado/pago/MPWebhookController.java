package br.com.escconsulting.controller.mercado.pago;

import br.com.escconsulting.service.mercado.pago.MPWebhookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mercado-pago/webhook")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MPWebhookController {

    private final MPWebhookService mpWebhookService;

    @PostMapping
    public ResponseEntity<String> webhook(@RequestBody String notification) throws JsonProcessingException, MPException, MPApiException {
        return mpWebhookService.webhook(notification);
    }
}