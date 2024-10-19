package br.com.escconsulting.dto.customer.vehicle.withdrawal.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CustomerVehicleWithdrawalRequestSearchDTO {

    private String cpf;
    private UUID paymentMethodId;
    private UUID paymentStatusId;
}