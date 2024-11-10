package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.country.CountryDTO;
import br.com.escconsulting.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    CountryDTO toDTO(Country country);

    void update(CountryDTO source, @MappingTarget Country target);
}