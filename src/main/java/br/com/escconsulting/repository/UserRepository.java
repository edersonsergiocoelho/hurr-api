package br.com.escconsulting.repository;

import br.com.escconsulting.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @EntityGraph(attributePaths = {
        "file",
        "roles"
    })
    Optional<User> findById(UUID userId);

    @EntityGraph(attributePaths = {
        "file",
        "roles"
    })
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}