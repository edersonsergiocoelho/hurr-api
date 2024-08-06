package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.address.address.type.AddressAddressTypeDTO;
import br.com.escconsulting.entity.AddressAddressType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressAddressTypeMapper {

    AddressAddressTypeMapper INSTANCE = Mappers.getMapper(AddressAddressTypeMapper.class);

    AddressAddressTypeDTO toDTO(AddressAddressType addressAddressType);
}