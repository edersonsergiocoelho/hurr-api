package br.com.escconsulting.dto.address.address.type;

import br.com.escconsulting.dto.address.AddressDTO;
import br.com.escconsulting.dto.address.type.AddressTypeDTO;
import br.com.escconsulting.entity.AddressAddressTypeId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressAddressTypeDTO {

    private AddressAddressTypeId id;
    private AddressDTO address;
    private AddressTypeDTO addressType;
}