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

    @Mapping(source = "user.file", target = "file")
    @Mapping(source = "user.roles", target = "roles")
    UserDTO toDTO(User user);

    @Mapping(source = "user.file", target = "file")
    @Mapping(source = "user.roles", target = "roles", ignore = true)
    UserDTO toNoRoleDTO(User user);

    @Mapping(source = "user.file", target = "file", ignore = true)
    @Mapping(source = "user.roles", target = "roles", ignore = true)
    UserDTO toSimpleDTO(User user);

    @Mapping(target = "roles", ignore = true)
    void update(User source, @MappingTarget User target);
}