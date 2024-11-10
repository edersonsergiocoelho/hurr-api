package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.address.AddressDTO;
import br.com.escconsulting.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "country", target = "country")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "state", target = "state")
    AddressDTO toDTO(Address address);

    @Mapping(target = "country", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target = "state", ignore = true)
    AddressDTO toDTOSimple(Address address);

    void update(Address source, @MappingTarget Address target);
}
