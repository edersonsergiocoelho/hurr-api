package br.com.escconsulting.controller.mercado.pago;

import br.com.escconsulting.service.mercado.pago.MPPaymentService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mercado-pago/payment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MPPaymentController {

    private final MPPaymentService mpPaymentService;

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> findById(@PathVariable("paymentId") Long paymentId) throws MPException, MPApiException {
        return mpPaymentService.findById(paymentId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
}