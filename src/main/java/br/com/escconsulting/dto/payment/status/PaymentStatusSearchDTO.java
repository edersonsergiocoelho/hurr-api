package br.com.escconsulting.dto.payment.status;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class PaymentStatusSearchDTO {

    @Setter
    @Getter
    private UUID customerId;
}