package br.com.escconsulting.repository;

import br.com.escconsulting.entity.UserPreference;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, UUID> {

    @EntityGraph(attributePaths = {
        "user"
    })
    Optional<UserPreference> findByUser_UserId(UUID userId);
}