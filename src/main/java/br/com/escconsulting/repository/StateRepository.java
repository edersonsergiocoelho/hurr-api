package br.com.escconsulting.repository;

import br.com.escconsulting.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StateRepository extends JpaRepository<State, UUID> {

    @Query("SELECT s FROM State s INNER JOIN FETCH s.country c WHERE c.countryId = :countryId")
    List<State> findByCountryId(@Param("countryId") UUID countryId);
}