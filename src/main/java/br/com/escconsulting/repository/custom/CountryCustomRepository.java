package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.country.CountrySearchDTO;
import br.com.escconsulting.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CountryCustomRepository extends JpaRepository<Country, UUID> {

    Page<Country> searchPage(CountrySearchDTO countrySearchDTO, Pageable pageable);

    Long countSearchPage(CountrySearchDTO countrySearchDTO);
}