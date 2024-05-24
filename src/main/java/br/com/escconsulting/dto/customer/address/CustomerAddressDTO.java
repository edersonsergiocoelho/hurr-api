package br.com.escconsulting.dto.customer.address;

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
    private UUID addressId;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}