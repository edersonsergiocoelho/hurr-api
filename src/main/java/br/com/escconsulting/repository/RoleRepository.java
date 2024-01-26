package br.com.escconsulting.repository;

import br.com.escconsulting.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

	boolean existsByRoleName(String roleName);
	Optional<Role> findByRoleName(String roleName);
	Page<Role> findByRoleNameContainingIgnoreCase(String roleName, Pageable pageable);

	Page<Role> findByRoleNameContainingIgnoreCaseAndEnabled(String roleName, Boolean enabled, Pageable pageable);

	Page<Role> findByEnabled(Boolean enabled, Pageable pageable);
}