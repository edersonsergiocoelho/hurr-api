package br.com.escconsulting.dto.vehicle;

import br.com.escconsulting.dto.vehicle.brand.VehicleBrandDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    private UUID vehicleId;
    private String vehicleName;
    private VehicleBrandDTO vehicleBrand;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}