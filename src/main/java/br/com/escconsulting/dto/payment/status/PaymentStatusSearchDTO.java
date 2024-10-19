package br.com.escconsulting.dto.payment.status;

import lombok.Getter;

@Getter
public class PaymentStatusSearchDTO {

    private String globalFilter;
    private String paymentStatusName;
    private Boolean enabled;
}