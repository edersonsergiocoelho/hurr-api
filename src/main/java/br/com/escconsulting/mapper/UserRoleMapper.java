package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.user.role.UserRoleDTO;
import br.com.escconsulting.entity.UserPreference;
import br.com.escconsulting.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRoleMapper {

    UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);

    @Mapping(source = "user",target = "user")
    @Mapping(source = "role",target = "role")
    UserRoleDTO toDTO(UserRole userRole);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserRoleDTO toDTOSimple(UserRole userRole);

    void update(UserRole source, @MappingTarget UserRole target);
}