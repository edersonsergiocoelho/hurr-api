package br.com.escconsulting.controller.mercado.pago;

import br.com.escconsulting.service.mercado.pago.MPPaymentCreateService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.net.MPResourceList;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.paymentmethod.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mercado-pago/payment-create")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MPPaymentCreateController {

    private final MPPaymentCreateService mpPaymentCreateService;

    @PostMapping
    public ResponseEntity<Payment> create() throws MPException, MPApiException {
        Payment mpResourceListPaymentMethod = mpPaymentCreateService.create();
        return ResponseEntity.ok(mpResourceListPaymentMethod);
    }

    @PostMapping("test")
    public ResponseEntity<Payment> createTest() throws MPException, MPApiException {
        Payment mpResourceListPaymentMethod = mpPaymentCreateService.createTest2();
        return ResponseEntity.ok(mpResourceListPaymentMethod);
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethod>> findAll() throws MPException, MPApiException {
        MPResourceList<PaymentMethod> mpResourceListPaymentMethod = mpPaymentCreateService.findAll();
        return ResponseEntity.ok(mpResourceListPaymentMethod.getResults());
    }
}