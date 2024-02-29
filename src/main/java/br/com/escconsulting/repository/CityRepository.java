package br.com.escconsulting.repository;

import br.com.escconsulting.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {

    @Query("SELECT c FROM City c INNER JOIN FETCH c.state s INNER JOIN FETCH s.country WHERE s.stateId = :stateId")
    List<City> findByStateId(@Param("stateId") UUID stateId);
}