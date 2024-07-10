package br.com.escconsulting.repository;

import br.com.escconsulting.entity.UserRole;
import br.com.escconsulting.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

}