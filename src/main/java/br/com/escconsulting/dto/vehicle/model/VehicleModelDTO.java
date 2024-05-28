package br.com.escconsulting.dto.vehicle.model;

import br.com.escconsulting.dto.vehicle.VehicleDTO;
import br.com.escconsulting.dto.vehicle.category.VehicleCategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleModelDTO {

    private UUID vehicleModelId;
    private String vehicleModelName;
    private VehicleDTO vehicle;
    private VehicleCategoryDTO vehicleCategory;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}