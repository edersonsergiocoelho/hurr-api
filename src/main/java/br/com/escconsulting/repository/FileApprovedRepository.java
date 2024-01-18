package br.com.escconsulting.repository;

import br.com.escconsulting.entity.FileApproved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileApprovedRepository extends JpaRepository<FileApproved, UUID> {

    Optional<FileApproved> findByFileId(UUID fileId);
}