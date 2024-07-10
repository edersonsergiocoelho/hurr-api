package br.com.escconsulting.repository.custom;

import br.com.escconsulting.dto.user.role.UserRoleDTO;
import br.com.escconsulting.dto.user.role.UserRoleSearchDTO;
import br.com.escconsulting.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleCustomRepository extends JpaRepository<UserRole, UUID> {

    Page<UserRoleDTO> searchPage(UserRoleSearchDTO userRoleSearchDTO, Pageable pageable);

    Long countSearchPage(UserRoleSearchDTO userRoleSearchDTO);
}