package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.menu.MenuDTO;
import br.com.escconsulting.entity.Menu;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    MenuDTO toDTO(Menu menu);
}