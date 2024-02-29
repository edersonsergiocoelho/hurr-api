package br.com.escconsulting.controller.mercado.pago;

import br.com.escconsulting.service.mercado.pago.MPPaymentMethodService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPResourceList;
import com.mercadopago.resources.paymentmethod.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mercado-pago/payment-method")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MPPaymentMethodController {

    private final MPPaymentMethodService mpPaymentMethodService;

    @GetMapping
    public ResponseEntity<List<PaymentMethod>> findAll() throws MPException, MPApiException {
        MPResourceList<PaymentMethod> mpResourceListPaymentMethod = mpPaymentMethodService.findAll();
        return ResponseEntity.ok(mpResourceListPaymentMethod.getResults());
    }
}