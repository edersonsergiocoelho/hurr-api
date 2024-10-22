package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.use.preference.UserPreferenceDTO;
import br.com.escconsulting.dto.use.preference.UserPreferenceSearchDTO;
import br.com.escconsulting.entity.UserPreference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserPreferenceService {

    Optional<UserPreference> findById(UUID userPreferenceId);

    Optional<UserPreference> findByUserId(UUID userId);

    List<UserPreference> findAll();

    Page<UserPreferenceDTO> searchPage(UserPreferenceSearchDTO userPreferenceSearchDTO, Pageable pageable);

    Optional<UserPreference> save(LocalUser localUser, UserPreference userPreference);

    Optional<UserPreference> update(UUID userPreferenceId, UserPreference userPreference);

    void delete(UUID userPreferenceId);

    void deleteAll(List<UUID> userPreferenceIds);
}