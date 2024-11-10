package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.use.preference.UserPreferenceDTO;
import br.com.escconsulting.entity.UserPreference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserPreferenceMapper {
    
    UserPreferenceMapper INSTANCE = Mappers.getMapper( UserPreferenceMapper.class );

    @Mapping(source = "user", target = "user")
    UserPreferenceDTO toDTO(UserPreference userPreference);

    @Mapping(target = "user", ignore = true)
    UserPreferenceDTO toDTOSimple(UserPreference userPreference);

    void update(UserPreference source, @MappingTarget UserPreference target);
}