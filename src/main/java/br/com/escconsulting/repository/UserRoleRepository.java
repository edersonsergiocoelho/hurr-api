package br.com.escconsulting.repository;

import br.com.escconsulting.dto.user.role.UserRoleSearchDTO;
import br.com.escconsulting.entity.UserRole;
import br.com.escconsulting.entity.UserRoleId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    public Page<UserRole> searchPage(UserRoleSearchDTO userRoleSearchDTO, Pageable pageable);
}