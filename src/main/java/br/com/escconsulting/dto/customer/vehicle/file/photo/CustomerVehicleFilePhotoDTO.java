package br.com.escconsulting.dto.customer.vehicle.file.photo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVehicleFilePhotoDTO {

    private UUID customerVehicleFilePhotoId;
    private String contentType;
    private String originalFileName;
    private byte[] dataAsByteArray;
    private Boolean coverPhoto;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}