package br.com.escconsulting.dto.vehicle.transmission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTransmissionDTO {

    private UUID vehicleTransmissionId;
    private String vehicleTransmissionName;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}