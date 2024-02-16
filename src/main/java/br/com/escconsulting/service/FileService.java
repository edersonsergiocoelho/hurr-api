package br.com.escconsulting.service;

import br.com.escconsulting.entity.File;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileService {
    Optional<File> findById(UUID fileId);

    List<File> findAll();

    Optional<File> save(File file);

    Optional<File> update(UUID fileId, File file);

    void delete(UUID fileId);
}