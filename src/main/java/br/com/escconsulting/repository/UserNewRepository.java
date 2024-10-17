package br.com.escconsulting.repository;

import br.com.escconsulting.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserNewRepository extends JpaRepository<User, UUID> {

    @EntityGraph(attributePaths = {
        "roles"
    })
    Optional<User> findById(UUID userId);

    @EntityGraph(attributePaths = {
            "roles"
    })
    Optional<User> findByEmail(String email);
}