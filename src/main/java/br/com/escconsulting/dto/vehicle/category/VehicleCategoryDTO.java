package br.com.escconsulting.dto.vehicle.category;

import br.com.escconsulting.dto.file.FileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCategoryDTO {

    private UUID vehicleCategoryId;
    private String vehicleCategoryName;
    private FileDTO file;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}