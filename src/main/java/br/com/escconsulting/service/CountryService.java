package br.com.escconsulting.service;

import br.com.escconsulting.dto.country.CountrySearchDTO;
import br.com.escconsulting.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CountryService {
    Optional<Country> findById(UUID countryId);

    List<Country> findAll();

    Page<Country> searchPage(CountrySearchDTO countrySearchDTO, Pageable pageable);

    Optional<Country> save(Country country);

    Optional<Country> update(UUID countryId, Country country);

    void delete(UUID countryId);
}