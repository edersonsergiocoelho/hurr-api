package br.com.escconsulting.dto.customer.bank.account;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class CustomerBankAccountSearchDTO {

    @Setter
    @Getter
    private UUID customerId;
}