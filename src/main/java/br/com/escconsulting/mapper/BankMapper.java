package br.com.escconsulting.mapper;

import br.com.escconsulting.dto.bank.BankDTO;
import br.com.escconsulting.entity.Bank;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankMapper {

    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    @Mapping(source = "file", target = "file")  // Isso mapeia o campo 'file' para o DTO correspondente.
    BankDTO toDTO(Bank bank);

    @Mapping(target = "file", ignore = true)  // Ignora o campo 'file' no mapeamento.
    BankDTO toDTOSimple(Bank bank);

    void update(Bank source, @MappingTarget Bank target);
}