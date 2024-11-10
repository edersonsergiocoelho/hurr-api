package br.com.escconsulting.dto.customer.address;

import br.com.escconsulting.dto.address.AddressDTO;
import br.com.escconsulting.dto.customer.CustomerDTO;
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
    private CustomerDTO customer;
    private AddressDTO address;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}