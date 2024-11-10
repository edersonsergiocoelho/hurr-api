package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.address.CustomerAddressDTO;
import br.com.escconsulting.entity.CustomerAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerAddressMapper {

    CustomerAddressMapper INSTANCE = Mappers.getMapper(CustomerAddressMapper.class);

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "address", target = "address")
    CustomerAddressDTO toDTO(CustomerAddress customerAddress);

    @Mapping(target = "customer", ignore = true)  // Ignora o mapeamento de customer
    @Mapping(target = "address", ignore = true)   // Ignora o mapeamento de address
    CustomerAddressDTO toDTOSimple(CustomerAddress customerAddress);

    void update(CustomerAddressDTO source, @MappingTarget CustomerAddress target);
}