package br.com.escconsulting.dto.payment.status;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class PaymentStatusSearchDTO {

    private String globalFilter;
    private String paymentStatusName;
    private Boolean enabled;
}