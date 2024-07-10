package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.user.role.UserRoleDTO;
import br.com.escconsulting.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserRoleMapper {

    UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);

    UserRoleDTO toDTO(UserRole userRole);
}