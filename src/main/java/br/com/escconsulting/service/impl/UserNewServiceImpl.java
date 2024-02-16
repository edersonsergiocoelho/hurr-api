package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.user.UserSearchDTO;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.repository.UserNewCustomRepository;
import br.com.escconsulting.repository.UserNewRepository;
import br.com.escconsulting.service.UserNewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserNewServiceImpl implements UserNewService {

    private final UserNewRepository userRepository;

    private final UserNewCustomRepository userNewCustomRepository;

    @Autowired
    public UserNewServiceImpl(@Lazy UserNewRepository userRepository, @Lazy UserNewCustomRepository userNewCustomRepository) {
        this.userRepository = userRepository;
        this.userNewCustomRepository = userNewCustomRepository;
    }

    @Transactional
    @Override
    public Optional<User> findById(UUID userId) {

        return Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with userId: " + userId)));
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public Page<User> searchPage(UserSearchDTO userSearchDTO, Pageable pageable) {
        return userNewCustomRepository.searchPage(userSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<User> save(User user) {

        user.setCreatedDate(Instant.now());
        user.setEnabled(Boolean.TRUE);

        return Optional.of(userRepository.save(user));
    }

    @Transactional
    @Override
    public Optional<User> update(UUID userId, User user) {
        return findById(userId)
                .map(existingUser -> {

                    existingUser.setEnabled(user.getEnabled());
                    existingUser.setModifiedDate(Instant.now());

                    existingUser.setPhotoValidated(user.getPhotoValidated());
                    existingUser.setPhotoFileId(user.getPhotoFileId());

                    return userRepository.save(existingUser);
                });
    }

    @Transactional
    @Override
    public void delete(UUID userId) {
        findById(userId).ifPresent(userRepository::delete);
    }
}