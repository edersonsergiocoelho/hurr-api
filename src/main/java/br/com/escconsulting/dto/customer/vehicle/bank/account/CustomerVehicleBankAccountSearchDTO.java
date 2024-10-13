package br.com.escconsulting.dto.customer.vehicle.bank.account;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class CustomerVehicleBankAccountSearchDTO {

    @Setter
    @Getter
    private UUID customerId;
}