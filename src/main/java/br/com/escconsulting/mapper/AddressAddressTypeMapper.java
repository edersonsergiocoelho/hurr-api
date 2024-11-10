package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.address.address.type.AddressAddressTypeDTO;
import br.com.escconsulting.entity.AddressAddressType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressAddressTypeMapper {

    AddressAddressTypeMapper INSTANCE = Mappers.getMapper(AddressAddressTypeMapper.class);

    @Mapping(source = "address", target = "address")  // Mapeia o campo "address"
    @Mapping(source = "addressType", target = "addressType")  // Mapeia o campo "addressType"
    AddressAddressTypeDTO toDTO(AddressAddressType addressAddressType);

    @Mapping(target = "address", ignore = true)  // Ignora a FK "address"
    @Mapping(target = "addressType", ignore = true)  // Ignora a FK "addressType"
    AddressAddressTypeDTO toDTOSimple(AddressAddressType addressAddressType);

    void update(AddressAddressType source, @MappingTarget AddressAddressType target);
}