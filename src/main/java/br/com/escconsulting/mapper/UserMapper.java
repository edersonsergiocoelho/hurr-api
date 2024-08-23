package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.user.UserDTO;
import br.com.escconsulting.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDTO toDTO(User user);

    @Mapping(source = "user.roles", target = "roles", ignore = true)
    UserDTO toSimpleDTO(User user);
}