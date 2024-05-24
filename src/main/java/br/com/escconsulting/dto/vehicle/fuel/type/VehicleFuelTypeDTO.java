package br.com.escconsulting.dto.vehicle.fuel.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleFuelTypeDTO {

    private UUID vehicleFuelTypeId;
    private String vehicleFuelTypeName;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}