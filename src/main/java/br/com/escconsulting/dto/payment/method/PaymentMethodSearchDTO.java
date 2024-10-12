package br.com.escconsulting.dto.payment.method;

import lombok.Getter;

@Getter
public class PaymentMethodSearchDTO {

    private String globalFilter;
    private String paymentMethodName;
    private Boolean enabled;
}