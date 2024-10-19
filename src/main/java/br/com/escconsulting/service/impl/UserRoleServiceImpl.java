package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.user.role.UserRoleDTO;
import br.com.escconsulting.dto.user.role.UserRoleSearchDTO;
import br.com.escconsulting.entity.Role;
import br.com.escconsulting.entity.UserRole;
import br.com.escconsulting.entity.UserRoleId;
import br.com.escconsulting.repository.UserRoleRepository;
import br.com.escconsulting.repository.custom.UserRoleCustomRepository;
import br.com.escconsulting.service.EmailService;
import br.com.escconsulting.service.RoleService;
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

    private final EmailService emailService;

    private final RoleService roleService;

    private final UserRoleRepository userRoleRepository;

    private final UserRoleCustomRepository userRoleCustomRepository;

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
    public Page<UserRoleDTO> searchPage(UserRoleSearchDTO userRoleSearchDTO, Pageable pageable) {
        return userRoleCustomRepository.searchPage(userRoleSearchDTO, pageable);
    }

    @Override
    public Optional<UserRole> save(UserRole userRole) {
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
    @Transactional
    public Optional<UserRole> becomeVehiclePartner(LocalUser localUser) {

        return userRoleRepository.findByUserEmail(localUser.getUsername())
                .map(existingUserRole -> {
                    // Deletar o UserRole atual
                    userRoleRepository.delete(existingUserRole);

                    Optional<Role> roleCustomerVehicle = roleService.findByRoleName("ROLE_CUSTOMER_VEHICLE");

                    // Criar um novo UserRole com a role ROLE_CUSTOMER_VEHICLE
                    UserRole userRoleNew = new UserRole();
                    userRoleNew.setUserRoleId(new UserRoleId(existingUserRole.getUser().getUserId(), roleCustomerVehicle.get().getRoleId())); // Supondo que o RoleId seja um UUID

                    userRoleNew.setUser(existingUserRole.getUser());
                    userRoleNew.setRole(roleCustomerVehicle.get());

                    // Salvar o novo UserRole
                    return userRoleRepository.save(userRoleNew);

                }).map(existingUserRole -> {
                    emailService.sendBecomeVehiclePartner(existingUserRole.getUser());
                    return existingUserRole;
                });
    }

    @Override
    public void delete(UUID userId, UUID roleId) {
        findById(userId, roleId).ifPresent(userRoleRepository::delete);
    }
}