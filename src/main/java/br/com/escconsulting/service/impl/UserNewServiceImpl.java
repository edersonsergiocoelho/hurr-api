package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.user.UserSearchDTO;
import br.com.escconsulting.entity.File;
import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.entity.enumeration.FileTable;
import br.com.escconsulting.entity.enumeration.FileType;
import br.com.escconsulting.repository.UserNewCustomRepository;
import br.com.escconsulting.repository.UserNewRepository;
import br.com.escconsulting.service.FileApprovedService;
import br.com.escconsulting.service.FileService;
import br.com.escconsulting.service.UserNewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserNewServiceImpl implements UserNewService {

    private final UserNewRepository userRepository;

    private final UserNewCustomRepository userNewCustomRepository;

    private final FileService fileService;

    private final FileApprovedService fileApprovedService;

    @Autowired
    public UserNewServiceImpl(@Lazy UserNewRepository userRepository, @Lazy UserNewCustomRepository userNewCustomRepository, @Lazy FileService fileService, @Lazy FileApprovedService fileApprovedService) {
        this.userRepository = userRepository;
        this.userNewCustomRepository = userNewCustomRepository;
        this.fileService = fileService;
        this.fileApprovedService = fileApprovedService;
    }

    @Transactional
    @Override
    public Optional<User> findById(UUID userId) {

        return Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with userId: " + userId)));
    }

    @Transactional
    @Override
    public Optional<User> findByEmail(String email) {

        return Optional.ofNullable(userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email)));
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public Page<User> searchPage(UserSearchDTO userSearchDTO, Pageable pageable) {
        return userNewCustomRepository.searchPage(userSearchDTO, pageable);
    }

    @Transactional
    @Override
    public Optional<FileApproved> upload(LocalUser localUser, MultipartFile[] files) throws IOException {

        MultipartFile multipartFile = Arrays.stream(files).findFirst().get();

        File file = new File();
        file.setContentType(multipartFile.getContentType());
        file.setOriginalFileName(multipartFile.getOriginalFilename());
        file.setDataAsByteArray(multipartFile.getBytes());
        file.setCreatedDate(Instant.now());
        file.setEnabled(Boolean.FALSE);

        file = fileService.save(file).get();

        Optional<User> userFindByEmail = userRepository.findByEmail(localUser.getUser().getEmail());

        if (userFindByEmail.isPresent()) {

            userFindByEmail.get().setPhotoFileId(file.getFileId());

            userRepository.save(userFindByEmail.get());

            FileApproved fileApproved = new FileApproved();

            fileApproved.setFileType(FileType.PROFILE_PICTURE);
            fileApproved.setFileTable(FileTable.USER);
            fileApproved.setUserId(userFindByEmail.get().getUserId());
            fileApproved.setFileId(file.getFileId());
            fileApproved.setCreatedBy(localUser.getUser().getUserId());
            fileApproved.setCreatedDate(Instant.now());
            fileApproved.setEnabled(Boolean.TRUE);

            return fileApprovedService.save(fileApproved);
        }

        return null;
    }

    @Transactional
    @Override
    public Optional<User> save(User user) {

        user.setCreatedDate(Instant.now());
        user.setEnabled(Boolean.TRUE);

        return Optional.of(userRepository.save(user));
    }

    @Transactional
    @Override
    public Optional<User> update(UUID userId, User user) {
        return findById(userId)
                .map(existingUser -> {

                    existingUser.setEnabled(user.getEnabled());
                    existingUser.setModifiedDate(Instant.now());

                    existingUser.setPhotoValidated(user.getPhotoValidated());
                    existingUser.setPhotoFileId(user.getPhotoFileId());

                    return userRepository.save(existingUser);
                });
    }

    @Transactional
    @Override
    public void delete(UUID userId) {
        findById(userId).ifPresent(userRepository::delete);
    }
}