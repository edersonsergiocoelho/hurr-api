package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.country.CountrySearchDTO;
import br.com.escconsulting.entity.Country;
import br.com.escconsulting.repository.CountryRepository;
import br.com.escconsulting.repository.custom.CountryCustomRepository;
import br.com.escconsulting.service.CountryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    private final CountryCustomRepository countryCustomRepository;

    @Transactional
    @Override
    public Optional<Country> findById(UUID countryId) {

        return Optional.ofNullable(countryRepository.findById(countryId)
                .orElseThrow(() -> new RuntimeException("Country not found with countryId: " + countryId)));
    }

    @Transactional
    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Transactional
    @Override
    public Page<Country> searchPage(CountrySearchDTO countrySearchDTO, Pageable pageable) {
        return countryCustomRepository.searchPage(countrySearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<Country> save(Country country) {

        country.setCreatedDate(Instant.now());
        country.setEnabled(Boolean.TRUE);

        return Optional.of(countryRepository.save(country));
    }

    @Transactional
    @Override
    public Optional<Country> update(UUID countryId, Country country) {
        return findById(countryId)
                .map(existingCountry -> {

                    existingCountry.setEnabled(country.getEnabled());
                    existingCountry.setModifiedDate(Instant.now());

                    return countryRepository.save(existingCountry);

                });
    }

    @Transactional
    @Override
    public void delete(UUID countryId) {
        findById(countryId).ifPresent(countryRepository::delete);
    }
}