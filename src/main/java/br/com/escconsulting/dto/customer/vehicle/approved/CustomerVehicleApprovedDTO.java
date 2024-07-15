package br.com.escconsulting.dto.customer.vehicle.approved;

import br.com.escconsulting.entity.CustomerVehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVehicleApprovedDTO {

    private UUID customerVehicleApprovedId;
    private CustomerVehicle customerVehicle;
    private UUID approvedBy;
    private UUID reprovedBy;
    private String message;
    private UUID createdBy;
    private UUID modifiedBy;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}