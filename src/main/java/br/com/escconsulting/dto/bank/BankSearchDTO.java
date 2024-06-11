package br.com.escconsulting.dto.bank;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class BankSearchDTO {

    @Setter
    @Getter
    private UUID customerId;
}