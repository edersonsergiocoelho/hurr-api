package br.com.escconsulting.dto.payment.method;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class PaymentMethodSearchDTO {

    @Setter
    @Getter
    private UUID customerId;
}