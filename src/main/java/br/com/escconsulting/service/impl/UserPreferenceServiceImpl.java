package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.use.preference.UserPreferenceDTO;
import br.com.escconsulting.dto.use.preference.UserPreferenceSearchDTO;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.entity.UserPreference;
import br.com.escconsulting.mapper.UserPreferenceMapper;
import br.com.escconsulting.repository.UserPreferenceRepository;
import br.com.escconsulting.repository.custom.UserPreferenceCustomRepository;
import br.com.escconsulting.service.UserService;
import br.com.escconsulting.service.UserPreferenceService;
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
public class UserPreferenceServiceImpl implements UserPreferenceService {

    private final UserService userService;

    private final UserPreferenceRepository userPreferenceRepository;

    private final UserPreferenceCustomRepository userPreferenceCustomRepository;

    @Transactional
    @Override
    public Optional<UserPreference> findById(UUID userPreferenceId) {
        return userPreferenceRepository.findById(userPreferenceId);
    }

    @Transactional
    @Override
    public Optional<UserPreference> findByUserId(UUID userId) {
        return userPreferenceRepository.findByUser_UserId(userId);
    }

    @Transactional
    @Override
    public List<UserPreference> findAll() {
        return userPreferenceRepository.findAll();
    }

    @Transactional
    @Override
    public Page<UserPreferenceDTO> searchPage(UserPreferenceSearchDTO userPreferenceSearchDTO, Pageable pageable) {
        return userPreferenceCustomRepository.searchPage(userPreferenceSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<UserPreference> save(LocalUser localUser, UserPreference userPreference) {

        Optional<User> optionalUser = userService.findByEmail(localUser.getUsername());

        return optionalUser.map(existingUser -> {
            userPreference.setUser(existingUser);
            return userPreferenceRepository.save(userPreference);
        });
    }

    @Transactional
    @Override
    public Optional<UserPreference> update(UUID userPreferenceId, UserPreference userPreference) {
        return findById(userPreferenceId)
                .map(existingUserPreference -> {

                    UserPreferenceMapper.INSTANCE.update(userPreference, existingUserPreference);

                    existingUserPreference.setModifiedDate(Instant.now());

                    return userPreferenceRepository.save(existingUserPreference);
                });
    }

    @Transactional
    @Override
    public void delete(UUID userPreferenceId) {
        findById(userPreferenceId).ifPresent(userPreferenceRepository::delete);
    }

    @Transactional
    @Override
    public void deleteAll(List<UUID> userPreferenceIds) {
        userPreferenceRepository.deleteAllById(userPreferenceIds);
    }
}