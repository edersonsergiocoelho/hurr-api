package br.com.escconsulting.dto.vehicle.color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleColorDTO {

    private UUID vehicleColorId;
    private String vehicleColorName;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}