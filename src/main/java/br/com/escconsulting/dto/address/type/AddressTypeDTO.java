package br.com.escconsulting.dto.address.type;

import br.com.escconsulting.dto.address.address.type.AddressAddressTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressTypeDTO {

    private UUID addressTypeId;
    private String addressTypeName;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}