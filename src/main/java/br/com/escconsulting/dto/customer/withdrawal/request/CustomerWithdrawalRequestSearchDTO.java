package br.com.escconsulting.dto.customer.withdrawal.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class CustomerWithdrawalRequestSearchDTO {

    private String cpf;
    private UUID paymentMethodId;
    private UUID paymentStatusId;
}