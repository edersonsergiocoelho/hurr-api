package br.com.escconsulting.dto.customer.address;

import br.com.escconsulting.dto.address.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressDTO {

    private UUID customerAddressId;
    private UUID customerId;
    private AddressDTO address;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}