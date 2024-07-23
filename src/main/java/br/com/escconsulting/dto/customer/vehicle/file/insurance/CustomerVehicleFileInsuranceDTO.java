package br.com.escconsulting.dto.customer.vehicle.file.insurance;

import br.com.escconsulting.entity.CustomerVehicle;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVehicleFileInsuranceDTO {

    private UUID customerVehicleFileInsuranceId;
    private String contentType;
    private String originalFileName;
    private byte[] dataAsByteArray;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}