package br.com.escconsulting.controller.mercado.pago;

import br.com.escconsulting.config.MPConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mercado-pago/webhook")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MPWebhookController {

    private final MPConfig mpConfig;

    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody String notification) {
        // Lógica para lidar com a notificação aqui
        System.out.println("Received Mercado Pago Webhook Notification: " + notification.toString());

        // Responda ao Mercado Pago com sucesso (HTTP 200 OK)
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}