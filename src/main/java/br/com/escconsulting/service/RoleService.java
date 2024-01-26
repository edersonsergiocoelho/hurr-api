package br.com.escconsulting.service;

import br.com.escconsulting.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {
    
    Optional<Role> findById(UUID id);

    List<Role> findAll();

    Page<Role> searchPage(String roleName, Boolean enabled, Pageable pageable);

    Optional<Role> save(Role role);

    Optional<Role> update(UUID id, Role role);

    void delete(UUID id);
}