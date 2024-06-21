package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.address.CustomerAddressDTO;
import br.com.escconsulting.entity.CustomerAddress;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerAddressMapper {

    CustomerAddressMapper INSTANCE = Mappers.getMapper( CustomerAddressMapper.class );

    CustomerAddressDTO toDTO(CustomerAddress customerVehicleBooking);
}