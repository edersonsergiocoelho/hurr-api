package br.com.escconsulting.dto.vehicle.brand;

import lombok.Getter;

@Getter
public class VehicleBrandSearchDTO {

    private String globalFilter;
    private String vehicleBrandName;
    private Boolean enabled;
}