package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.user.role.UserRoleSearchDTO;
import br.com.escconsulting.entity.UserRole;
import br.com.escconsulting.entity.UserRoleId;
import br.com.escconsulting.repository.UserRoleRepository;
import br.com.escconsulting.service.UserRoleService;
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
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Override
    public Optional<UserRole> findById(UUID userId, UUID roleId) {

        UserRoleId id = new UserRoleId();
        id.setUserId(userId);
        id.setRoleId(roleId);

        return Optional.ofNullable(userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserRole not found with id: " + id)));
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public Page<UserRole> searchPage(UserRoleSearchDTO userRoleSearchDTO, Pageable pageable) {
        return userRoleRepository.searchPage(userRoleSearchDTO, pageable);
    }

    @Override
    public Optional<UserRole> save(UserRole userRole) {

        userRole.setCreatedDate(Instant.now());
        userRole.setEnabled(Boolean.TRUE);

        return Optional.of(userRoleRepository.save(userRole));
    }

    @Override
    public List<UserRole> saveAll(List<UserRole> userRoles) {
        return userRoleRepository.saveAll(userRoles);
    }

    @Override
    @Transactional
    public Optional<UserRole> update(UUID userId, UUID roleId, UserRole userRole) {
        return findById(userId, roleId)
                .map(existingUserRole -> {

                    existingUserRole.setEnabled(userRole.getEnabled());
                    existingUserRole.setModifiedDate(Instant.now());

                    if (!existingUserRole.getUserRoleId().getRoleId().equals(userRole.getUserRoleId().getRoleId())) {

                        userRoleRepository.delete(existingUserRole);

                        return userRoleRepository.save(userRole);
                    }

                    return userRoleRepository.save(existingUserRole);
                });
    }

    @Override
    public void delete(UUID userId, UUID roleId) {
        findById(userId, roleId).ifPresent(userRoleRepository::delete);
    }
}