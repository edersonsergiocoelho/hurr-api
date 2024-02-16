package br.com.escconsulting.service;

import br.com.escconsulting.dto.file.approved.FileApprovedSearchDTO;
import br.com.escconsulting.entity.FileApproved;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileApprovedService {
    Optional<FileApproved> findById(UUID fileApprovedId);

    Optional<FileApproved> findByFileId(UUID fileId);

    List<FileApproved> findAll();

    Page<FileApproved> searchPage(FileApprovedSearchDTO fileApprovedSearchDTO, Pageable pageable);

    Optional<FileApproved> save(FileApproved fileApproved);

    Optional<FileApproved> update(UUID fileApprovedId, FileApproved fileApproved);

    void delete(UUID fileApprovedId);
}