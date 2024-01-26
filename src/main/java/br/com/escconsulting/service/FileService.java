package br.com.escconsulting.service;

import br.com.escconsulting.entity.File;

import java.util.List;
import java.util.UUID;

public interface FileService {
    File findById(UUID id);

    List<File> findAll();

    File save(File review);

    File update(UUID id, File file);

    void delete(UUID id);
}