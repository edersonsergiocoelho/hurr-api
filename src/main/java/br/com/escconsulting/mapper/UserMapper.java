package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.user.UserDTO;
import br.com.escconsulting.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(source = "file", target = "file")
    @Mapping(source = "roles", target = "roles")
    UserDTO toDTO(User user);

    @Mapping(source = "file", target = "file")
    @Mapping(source = "roles", target = "roles", ignore = true)
    UserDTO toDTONoRole(User user);

    @Mapping(target = "file", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserDTO toSimpleDTO(User user);

    @Mapping(target = "roles", ignore = true)
    void update(User source, @MappingTarget User target);
}