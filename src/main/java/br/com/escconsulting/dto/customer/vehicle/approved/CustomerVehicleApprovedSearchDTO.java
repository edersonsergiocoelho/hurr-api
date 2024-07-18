package br.com.escconsulting.dto.customer.vehicle.approved;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CustomerVehicleApprovedSearchDTO {

    private UUID vehicleBrandId;
    private UUID vehicleId;
    private UUID vehicleModelId;
    private String firstName;
    private String lastName;
    private String cpf;
}