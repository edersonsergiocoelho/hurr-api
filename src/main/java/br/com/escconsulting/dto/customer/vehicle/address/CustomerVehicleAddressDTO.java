package br.com.escconsulting.dto.customer.vehicle.address;

import br.com.escconsulting.dto.address.AddressDTO;
import br.com.escconsulting.dto.customer.vehicle.CustomerVehicleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVehicleAddressDTO {

    private UUID customerVehicleAddressId;
    private AddressDTO address;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}