package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.file.approved.FileApprovedSearchDTO;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.entity.enumeration.FileTable;
import br.com.escconsulting.entity.enumeration.FileType;
import br.com.escconsulting.repository.custom.FileApprovedCustomRepository;
import br.com.escconsulting.repository.FileApprovedRepository;
import br.com.escconsulting.service.CustomerService;
import br.com.escconsulting.service.EmailService;
import br.com.escconsulting.service.FileApprovedService;
import br.com.escconsulting.service.UserNewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileApprovedServiceImpl implements FileApprovedService {

    private final CustomerService customerService;

    private final EmailService emailService;

    private final UserNewService userNewService;

    private final FileApprovedRepository fileApprovedRepository;

    private final FileApprovedCustomRepository fileApprovedCustomRepository;

    @Autowired
    public FileApprovedServiceImpl(@Lazy CustomerService customerService, @Lazy EmailService emailService, @Lazy UserNewService userNewService, @Lazy FileApprovedRepository fileApprovedRepository, @Lazy FileApprovedCustomRepository fileApprovedCustomRepository) {
        this.customerService = customerService;
        this.emailService = emailService;
        this.userNewService = userNewService;
        this.fileApprovedRepository = fileApprovedRepository;
        this.fileApprovedCustomRepository = fileApprovedCustomRepository;
    }

    @Override
    public Optional<FileApproved> findById(UUID fileApprovedId) {

        return Optional.ofNullable(fileApprovedRepository.findById(fileApprovedId)
                .orElseThrow(() -> new RuntimeException("File Approved not found with fileApprovedId: " + fileApprovedId)));
    }

    @Override
    public Optional<FileApproved> findByFileId(UUID fileId) {
        return Optional.ofNullable(fileApprovedRepository.findByFileId(fileId)
                .orElseThrow(() -> new RuntimeException("File Approved not found with fileId: " + fileId)));
    }

    @Override
    public List<FileApproved> findAll() {
        return fileApprovedRepository.findAll();
    }

    @Override
    public Page<FileApproved> searchPage(FileApprovedSearchDTO fileApprovedSearchDTO, Pageable pageable) {
        return fileApprovedCustomRepository.searchPage(fileApprovedSearchDTO, pageable);
    }

    @Override
    public Optional<FileApproved> save(FileApproved fileApproved) {

        fileApproved.setCreatedDate(Instant.now());
        fileApproved.setEnabled(Boolean.TRUE);

        return Optional.of(fileApprovedRepository.save(fileApproved));
    }

    @Override
    @Transactional
    public Optional<FileApproved> update(UUID fileApprovedId, FileApproved fileApproved) {
        return findById(fileApprovedId)
                .map(existingFileApproved -> {

                    existingFileApproved.setEnabled(fileApproved.getEnabled());
                    existingFileApproved.setModifiedDate(Instant.now());

                    existingFileApproved.setMessage(fileApproved.getMessage());

                    if (fileApproved.getApprovedBy() != null) {
                        existingFileApproved.setApprovedBy(fileApproved.getApprovedBy());
                    }

                    if (fileApproved.getReprovedBy() != null) {
                        existingFileApproved.setReprovedBy(fileApproved.getReprovedBy());
                    }

                    return fileApprovedRepository.save(existingFileApproved);

                }).map(savedFileApproved -> {

                    if (fileApproved.getFileTable().equals(FileTable.CUSTOMER)) {
                        if (fileApproved.getFileType().equals(FileType.DRIVER_LICENSE)) {

                            Optional<Customer> optionalCustomer = customerService.findById(fileApproved.getCustomerId());

                            if (optionalCustomer.isPresent()) {

                                if (fileApproved.getApprovedBy() != null) {
                                    emailService.sendDriverLicenseApproved(savedFileApproved, optionalCustomer.get());
                                }

                                if (fileApproved.getReprovedBy() != null) {
                                    emailService.sendDriverLicenseDisapprove(savedFileApproved, optionalCustomer.get());
                                }
                            }
                        }

                        if (fileApproved.getFileType().equals(FileType.IDENTITY_NUMBER)) {

                            Optional<Customer> optionalCustomer = customerService.findById(fileApproved.getCustomerId());

                            if (optionalCustomer.isPresent()) {

                                if (fileApproved.getApprovedBy() != null) {
                                    emailService.sendIdentityNumberApproved(savedFileApproved, optionalCustomer.get());
                                }

                                if (fileApproved.getReprovedBy() != null) {
                                    emailService.sendIdentityNumberReproved(savedFileApproved, optionalCustomer.get());
                                }
                            }
                        }
                    }

                    if (fileApproved.getFileTable().equals(FileTable.USER)) {
                        if (fileApproved.getFileType().equals(FileType.PROFILE_PICTURE)) {

                            Optional<User> optionalUser = userNewService.findById(fileApproved.getUserId());

                            if (optionalUser.isPresent()) {

                                if (fileApproved.getApprovedBy() != null) {
                                    emailService.sendProfilePictureApproved(savedFileApproved, optionalUser.get());
                                }

                                if (fileApproved.getReprovedBy() != null) {
                                    emailService.sendProfilePictureReproved(savedFileApproved, optionalUser.get());
                                }
                            }
                        }
                    }

                    return savedFileApproved;
                });
    }

    @Override
    public void delete(UUID fileApprovedId) {
        findById(fileApprovedId).ifPresent(fileApprovedRepository::delete);
    }
}