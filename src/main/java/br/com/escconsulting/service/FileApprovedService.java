package br.com.escconsulting.service;

import br.com.escconsulting.entity.FileApproved;

import java.util.List;
import java.util.UUID;

public interface FileApprovedService {
    FileApproved findById(UUID fileApprovedId);

    FileApproved findByFileId(UUID fileId);

    List<FileApproved> findAll();

    FileApproved save(FileApproved fileApproved);

    FileApproved update(UUID fileApprovedId, FileApproved fileApproved);

    void delete(UUID fileApprovedId);
}