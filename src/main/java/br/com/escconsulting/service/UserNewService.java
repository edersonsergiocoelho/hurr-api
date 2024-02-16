package br.com.escconsulting.service;

import br.com.escconsulting.dto.user.UserSearchDTO;
import br.com.escconsulting.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserNewService {
    Optional<User> findById(UUID userId);

    List<User> findAll();

    Page<User> searchPage(UserSearchDTO userSearchDTO, Pageable pageable);

    Optional<User> save(User user);

    Optional<User> update(UUID userId, User user);

    void delete(UUID userId);
}