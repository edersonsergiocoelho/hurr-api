package br.com.escconsulting.dto.customer.vehicle;

import lombok.Getter;

import java.util.UUID;

@Getter
public class SearchCustomerVehicle {

    private UUID vehicleId;
    private UUID vehicleModelId;
    private UUID vehicleBrandId;
    private UUID vehicleCategoryId;
    private UUID vehicleColorId;
    private UUID vehicleFuelTypeId;
    private UUID vehicleTransmissionId;

    private String countryName;
    private String stateName;
    private String cityName;
}