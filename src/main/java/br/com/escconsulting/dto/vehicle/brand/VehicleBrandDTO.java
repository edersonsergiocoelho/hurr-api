package br.com.escconsulting.dto.vehicle.brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleBrandDTO {

    private UUID vehicleBrandId;
    private String vehicleBrandName;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}