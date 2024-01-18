package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.File;
import br.com.escconsulting.repository.FileRepository;
import br.com.escconsulting.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public File findById(UUID fileId) {
        return fileRepository.findById(fileId)
                             .orElseThrow(() -> new RuntimeException("File not found with fileId: " + fileId));
    }

    @Override
    public List<File> findAll() {
        return fileRepository.findAll();
    }

    @Override
    public File save(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File update(UUID fileId, File file) {
        File fileUpdated = findById(fileId);
        return fileRepository.save(fileUpdated);
    }

    @Override
    public void delete(UUID fileId) {
        File file = findById(fileId);
        fileRepository.delete(file);
    }
}