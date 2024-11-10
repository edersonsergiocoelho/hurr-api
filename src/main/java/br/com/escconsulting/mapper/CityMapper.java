package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.city.CityDTO;
import br.com.escconsulting.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mapping(source = "state", target = "state")  // Mapeia o campo 'state' para o DTO correspondente.
    CityDTO toDTO(City city);

    @Mapping(target = "state", ignore = true)  // Ignora o campo 'state' no mapeamento.
    CityDTO toDTOSimple(City city);

    void update(CityDTO source, @MappingTarget City target);
}