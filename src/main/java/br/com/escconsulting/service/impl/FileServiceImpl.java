package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.File;
import br.com.escconsulting.repository.FileRepository;
import br.com.escconsulting.service.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public Optional<File> findById(UUID fileId) {

        return Optional.ofNullable(fileRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("File Approved not found with fileId: " + fileId)));
    }

    @Override
    public List<File> findAll() {
        return fileRepository.findAll();
    }

    @Override
    public Optional<File> save(File file) {

        file.setCreatedDate(Instant.now());
        file.setEnabled(Boolean.TRUE);

        return Optional.of(fileRepository.save(file));
    }

    @Override
    @Transactional
    public Optional<File> update(UUID fileId, File file) {
        return findById(fileId)
                .map(existingFile -> {

                    existingFile.setEnabled(file.getEnabled());
                    existingFile.setModifiedDate(Instant.now());

                    return fileRepository.save(existingFile);
                });
    }

    @Override
    public void delete(UUID fileId) {
        findById(fileId).ifPresent(fileRepository::delete);
    }
}