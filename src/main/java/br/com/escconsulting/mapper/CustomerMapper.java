package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.customer.CustomerDTO;
import br.com.escconsulting.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    CustomerDTO toDTO(Customer customer);

    void update(CustomerDTO source, @MappingTarget Customer target);
}