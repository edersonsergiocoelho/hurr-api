package br.com.escconsulting.service;

import br.com.escconsulting.dto.city.CitySearchDTO;
import br.com.escconsulting.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CityService {
    Optional<City> findById(UUID cityId);

    List<City> findByStateId(UUID stateId);

    List<City> findAll();

    Page<City> searchPage(CitySearchDTO citySearchDTO, Pageable pageable);

    Optional<City> save(City city);

    Optional<City> update(UUID cityId, City city);

    void delete(UUID cityId);
}