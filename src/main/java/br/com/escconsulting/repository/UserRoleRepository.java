package br.com.escconsulting.repository;

import br.com.escconsulting.entity.UserRole;
import br.com.escconsulting.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    @Query("SELECT ur FROM UserRole ur WHERE ur.user.email = :email")
    Optional<UserRole> findByUserEmail(@Param("email") String email);
}