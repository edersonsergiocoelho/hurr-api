package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.type.menu.TypeMenuDTO;
import br.com.escconsulting.entity.TypeMenu;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeMenuMapper {

    TypeMenuMapper INSTANCE = Mappers.getMapper(TypeMenuMapper.class);

    TypeMenuDTO toDTO(TypeMenu typeMenu);

    void update(TypeMenu source, @MappingTarget TypeMenu target);
}