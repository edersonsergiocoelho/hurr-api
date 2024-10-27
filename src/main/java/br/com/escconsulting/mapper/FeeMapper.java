package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.fee.FeeDTO;
import br.com.escconsulting.entity.Fee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FeeMapper {
    
    FeeMapper INSTANCE = Mappers.getMapper( FeeMapper.class );

    FeeDTO toDTO(Fee fee);

    void update(Fee source, @MappingTarget Fee target);
}