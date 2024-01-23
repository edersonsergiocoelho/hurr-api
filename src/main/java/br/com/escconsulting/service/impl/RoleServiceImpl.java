package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.Role;
import br.com.escconsulting.exception.role.RoleNameDuplicatedException;
import br.com.escconsulting.repository.RoleRepository;
import br.com.escconsulting.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findById(UUID id) {
        return Optional.ofNullable(roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id)));
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Page<Role> searchPage(String roleName, Boolean enabled, Pageable pageable) {
        Page<Role> resultPage;

        if (! StringUtils.isEmpty(roleName) && enabled != null) {
            resultPage = roleRepository.findByRoleNameContainingIgnoreCaseAndEnabled(roleName, enabled, pageable);
        } else if (! StringUtils.isEmpty(roleName)) {
            resultPage = roleRepository.findByRoleNameContainingIgnoreCase(roleName, pageable);
        } else if (enabled != null) {
            resultPage = roleRepository.findByEnabled(enabled, pageable);
        } else {
            resultPage = roleRepository.findAll(pageable);
        }

        return resultPage;
    }

    @Override
    public Optional<Role> save(Role role) {

        if (roleRepository.existsByRoleName(role.getRoleName())) {
            throw new RoleNameDuplicatedException("Role name already exists: " + role.getRoleName());
        }

        role.setCreatedDate(Instant.now());
        role.setEnabled(Boolean.TRUE);

        return Optional.of(roleRepository.save(role));
    }

    @Override
    public Optional<Role> update(UUID id, Role role) {

        if (roleRepository.existsByRoleName(role.getRoleName())) {
            throw new RoleNameDuplicatedException("Role name already exists: " + role.getRoleName());
        }

        return findById(id)
                .map(existingRole -> {

                    existingRole.setRoleName(role.getRoleName());
                    existingRole.setEnabled(role.getEnabled());
                    existingRole.setModifiedDate(Instant.now());

                    return roleRepository.save(existingRole);
                });
    }

    @Override
    public void delete(UUID id) {
        findById(id).ifPresent(roleRepository::delete);
    }
}