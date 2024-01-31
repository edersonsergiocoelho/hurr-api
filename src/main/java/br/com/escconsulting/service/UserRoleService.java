package br.com.escconsulting.service;

import br.com.escconsulting.dto.user.role.UserRoleSearchDTO;
import br.com.escconsulting.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRoleService {
    Optional<UserRole> findById(UUID userId, UUID roleId);

    List<UserRole> findAll();

    Page<UserRole> searchPage(UserRoleSearchDTO userRoleSearchDTO, Pageable pageable);

    Optional<UserRole> save(UserRole userUserRole);

    List<UserRole> saveAll(List<UserRole> userRoles);

    Optional<UserRole> update(UUID userId, UUID roleId, UserRole userUserRole);

    void delete(UUID userId, UUID roleId);
}