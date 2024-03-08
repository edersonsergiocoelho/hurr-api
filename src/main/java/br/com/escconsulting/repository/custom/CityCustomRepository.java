package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.city.CitySearchDTO;
import br.com.escconsulting.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityCustomRepository extends JpaRepository<City, UUID> {

    Page<City> searchPage(CitySearchDTO citySearchDTO, Pageable pageable);

    Long countSearchPage(CitySearchDTO citySearchDTO);
}