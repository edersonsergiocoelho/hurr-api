package br.com.escconsulting.dto.customer.vehicle.address;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CustomerVehicleAddressSearchDTO {

    private UUID customerVehicleId;
    private String nickname;
    private Boolean enabled;
}