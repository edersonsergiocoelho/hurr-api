package br.com.escconsulting.service.impl;

import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.repository.FileApprovedRepository;
import br.com.escconsulting.service.FileApprovedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileApprovedServiceImpl implements FileApprovedService {

    private final FileApprovedRepository fileApprovedRepository;

    @Override
    public FileApproved findById(UUID fileApprovedId) {
        return fileApprovedRepository.findById(fileApprovedId)
                                     .orElseThrow(() -> new RuntimeException("File Approved not found with fileApprovedId: " + fileApprovedId));
    }

    @Override
    public FileApproved findByFileId(UUID fileId) {
        return fileApprovedRepository.findByFileId(fileId)
                                     .orElseThrow(() -> new RuntimeException("File Approved not found with fileId: " + fileId));
    }

    @Override
    public List<FileApproved> findAll() {
        return fileApprovedRepository.findAll();
    }

    @Override
    public FileApproved save(FileApproved fileApproved) {
        return fileApprovedRepository.save(fileApproved);
    }

    @Override
    public FileApproved update(UUID fileApprovedId, FileApproved fileApproved) {
        FileApproved fileApprovedUpdated = findById(fileApprovedId);

        return fileApprovedRepository.save(fileApprovedUpdated);
    }

    @Override
    public void delete(UUID fileApprovedId) {
        FileApproved fileApproved = findById(fileApprovedId);
        fileApprovedRepository.delete(fileApproved);
    }
}