package br.com.escconsulting.repository;

import br.com.escconsulting.dto.file.approved.FileApprovedSearchDTO;
import br.com.escconsulting.entity.FileApproved;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileApprovedCustomRepository extends JpaRepository<FileApproved, UUID> {

    Page<FileApproved> searchPage(FileApprovedSearchDTO fileApprovedSearchDTO, Pageable pageable);

    Long countSearchPage(FileApprovedSearchDTO fileApprovedSearchDTO);
}