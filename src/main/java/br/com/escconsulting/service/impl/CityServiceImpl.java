package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.city.CitySearchDTO;
import br.com.escconsulting.entity.City;
import br.com.escconsulting.repository.CityRepository;
import br.com.escconsulting.repository.custom.CityCustomRepository;
import br.com.escconsulting.service.CityService;
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
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final CityCustomRepository cityCustomRepository;

    @Override
    public Optional<City> findById(UUID cityId) {

        return Optional.ofNullable(cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City not found with cityId: " + cityId)));
    }

    @Override
    public List<City> findByStateId(UUID stateId) {
        return cityRepository.findByStateId(stateId);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Page<City> searchPage(CitySearchDTO citySearchDTO, Pageable pageable) {
        return cityCustomRepository.searchPage(citySearchDTO, pageable);
    }

    @Override
    public Optional<City> save(City city) {

        city.setCreatedDate(Instant.now());
        city.setEnabled(Boolean.TRUE);

        return Optional.of(cityRepository.save(city));
    }

    @Override
    @Transactional
    public Optional<City> update(UUID cityId, City city) {
        return findById(cityId)
                .map(existingCity -> {

                    existingCity.setEnabled(city.getEnabled());
                    existingCity.setModifiedDate(Instant.now());

                    return cityRepository.save(existingCity);

                });
    }

    @Override
    public void delete(UUID cityId) {
        findById(cityId).ifPresent(cityRepository::delete);
    }
}