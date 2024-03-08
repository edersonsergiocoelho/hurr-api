package br.com.escconsulting.dto.customer.vehicle.booking;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class CustomerVehicleBookingSearchDTO {

    @Setter
    @Getter
    private UUID customerId;
}