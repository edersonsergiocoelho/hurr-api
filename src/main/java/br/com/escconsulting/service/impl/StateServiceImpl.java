package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.state.StateSearchDTO;
import br.com.escconsulting.entity.State;
import br.com.escconsulting.repository.StateRepository;
import br.com.escconsulting.repository.custom.StateCustomRepository;
import br.com.escconsulting.service.StateService;
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
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    private final StateCustomRepository stateCustomRepository;

    @Override
    @Transactional
    public Optional<State> findById(UUID stateId) {

        return Optional.ofNullable(stateRepository.findById(stateId)
                .orElseThrow(() -> new RuntimeException("State not found with stateId: " + stateId)));
    }

    @Override
    @Transactional
    public List<State> findByCountryId(UUID countryId) {
        return stateRepository.findByCountryId(countryId);
    }

    @Override
    @Transactional
    public List<State> findAll() {
        return stateRepository.findAll();
    }

    @Override
    @Transactional
    public Page<State> searchPage(StateSearchDTO stateSearchDTO, Pageable pageable) {
        return stateCustomRepository.searchPage(stateSearchDTO, pageable);
    }

    @Override
    @Transactional
    public Optional<State> save(State state) {

        state.setCreatedDate(Instant.now());
        state.setEnabled(Boolean.TRUE);

        return Optional.of(stateRepository.save(state));
    }

    @Override
    @Transactional
    public Optional<State> update(UUID stateId, State state) {
        return findById(stateId)
                .map(existingState -> {

                    existingState.setEnabled(state.getEnabled());
                    existingState.setModifiedDate(Instant.now());

                    return stateRepository.save(existingState);

                });
    }

    @Override
    @Transactional
    public void delete(UUID stateId) {
        findById(stateId).ifPresent(stateRepository::delete);
    }
}