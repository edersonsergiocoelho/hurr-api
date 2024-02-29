package br.com.escconsulting.service;

import br.com.escconsulting.dto.state.StateSearchDTO;
import br.com.escconsulting.entity.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StateService {
    Optional<State> findById(UUID stateId);

    List<State> findByCountryId(UUID countryId);

    List<State> findAll();

    Page<State> searchPage(StateSearchDTO stateSearchDTO, Pageable pageable);

    Optional<State> save(State state);

    Optional<State> update(UUID stateId, State state);

    void delete(UUID stateId);
}