package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.user.UserForgotPasswordDTO;
import br.com.escconsulting.dto.user.UserPasswordValidateCodeDTO;
import br.com.escconsulting.dto.user.UserPasswordVerificationCodeDTO;
import br.com.escconsulting.dto.user.UserSearchDTO;
import br.com.escconsulting.entity.File;
import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.entity.enumeration.FileTable;
import br.com.escconsulting.entity.enumeration.FileType;
import br.com.escconsulting.exception.user.UserEmailNotFoundException;
import br.com.escconsulting.exception.user.UserForgotPasswordValidatedException;
import br.com.escconsulting.repository.UserNewCustomRepository;
import br.com.escconsulting.repository.UserNewRepository;
import br.com.escconsulting.service.EmailService;
import br.com.escconsulting.service.FileApprovedService;
import br.com.escconsulting.service.FileService;
import br.com.escconsulting.service.UserNewService;
import br.com.escconsulting.util.RandomCodeGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final EmailService emailService;
    private final FileApprovedService fileApprovedService;
    private final FileService fileService;
    private final PasswordEncoder passwordEncoder;
    private final UserNewCustomRepository userNewCustomRepository;
    private final UserNewRepository userRepository;

    @Autowired
    public UserNewServiceImpl(@Lazy EmailService emailService, @Lazy FileApprovedService fileApprovedService, @Lazy FileService fileService, @Lazy PasswordEncoder passwordEncoder, @Lazy UserNewCustomRepository userNewCustomRepository, @Lazy UserNewRepository userRepository) {
        this.emailService = emailService;
        this.fileApprovedService = fileApprovedService;
        this.fileService = fileService;
        this.passwordEncoder = passwordEncoder;
        this.userNewCustomRepository = userNewCustomRepository;
        this.userRepository = userRepository;
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
    public Optional<User> forgotPasswordVerificationCode(UserPasswordVerificationCodeDTO userPasswordVerificationCodeDTO) {
        return userRepository.findByEmail(userPasswordVerificationCodeDTO.getEmail())
                .map(userToUpdate -> {

                    userToUpdate.setForgotPasswordVerificationCode(RandomCodeGenerator.generateCode(6).toUpperCase());
                    userToUpdate.setForgotPasswordValidated(Boolean.FALSE);

                    userRepository.save(userToUpdate);

                    return Optional.of(userToUpdate);
                })
                .orElseThrow(() -> new UserEmailNotFoundException("User email already exists: : " + userPasswordVerificationCodeDTO.getEmail()))
                .map(user -> {
                    emailService.sendForgotPasswordVerificationCode(user);
                    return user;
                });
    }

    @Transactional
    @Override
    public Optional<User> forgotPasswordValidated(UserPasswordValidateCodeDTO userPasswordValidateCodeDTO) {
        return userRepository.findByEmail(userPasswordValidateCodeDTO.getEmail())
                .map(userToUpdate -> {

                    if (! userToUpdate.getForgotPasswordVerificationCode().equals(userPasswordValidateCodeDTO.getForgotPasswordVerificationCode())) {
                        throw new UserForgotPasswordValidatedException("User - Forgot Password Verification Code Not Valid");
                    }

                    userToUpdate.setForgotPasswordValidated(Boolean.TRUE);

                    userRepository.save(userToUpdate);

                    return Optional.of(userToUpdate);
                })
                .orElseThrow(() -> new UserEmailNotFoundException("User email already exists: : " + userPasswordValidateCodeDTO.getEmail()))
                .map(user -> {
                    emailService.sendForgotPasswordValidated(user);
                    return user;
                });
    }

    @Transactional
    @Override
    public Optional<User> forgotPassword(UserForgotPasswordDTO userForgotPasswordDTO) {
        return findByEmail(userForgotPasswordDTO.getEmail())
                .map(userToUpdate -> {

                    userToUpdate.setForgotPasswordVerificationCode("");
                    userToUpdate.setPassword(passwordEncoder.encode(userForgotPasswordDTO.getPassword()));

                    userRepository.save(userToUpdate);

                    return Optional.of(userToUpdate);
                })
                .orElseThrow(() -> new UserEmailNotFoundException("User email already exists: : " + userForgotPasswordDTO.getEmail()))
                .map(user -> {
                    emailService.sendForgotPassword(user);
                    return user;
                });
    }

    @Transactional
    @Override
    public void delete(UUID userId) {
        findById(userId).ifPresent(userRepository::delete);
    }
}