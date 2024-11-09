package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.SignUpRequest;
import br.com.escconsulting.dto.SocialProvider;
import br.com.escconsulting.dto.user.UserForgotPasswordDTO;
import br.com.escconsulting.dto.user.UserPasswordValidateCodeDTO;
import br.com.escconsulting.dto.user.UserPasswordVerificationCodeDTO;
import br.com.escconsulting.dto.user.UserSearchDTO;
import br.com.escconsulting.entity.File;
import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.entity.Role;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.entity.enumeration.FileTable;
import br.com.escconsulting.entity.enumeration.FileType;
import br.com.escconsulting.exception.OAuth2AuthenticationProcessingException;
import br.com.escconsulting.exception.UserAlreadyExistAuthenticationException;
import br.com.escconsulting.exception.user.UserEmailNotFoundException;
import br.com.escconsulting.exception.user.UserForgotPasswordValidatedException;
import br.com.escconsulting.mapper.UserMapper;
import br.com.escconsulting.repository.UserRepository;
import br.com.escconsulting.repository.custom.UserCustomRepository;
import br.com.escconsulting.security.oauth2.model.OAuth2UserInfo;
import br.com.escconsulting.security.oauth2.model.OAuth2UserInfoFactory;
import br.com.escconsulting.service.*;
import br.com.escconsulting.util.GeneralUtils;
import br.com.escconsulting.util.RandomCodeGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final EmailService emailService;

    private final FileApprovedService fileApprovedService;

    private final FileService fileService;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    private final UserCustomRepository userCustomRepository;

    private final UserRepository userRepository;

    /*
    @Autowired
    public UserNewServiceImpl(@Lazy EmailService emailService, @Lazy FileApprovedService fileApprovedService, @Lazy FileService fileService, @Lazy PasswordEncoder passwordEncoder, @Lazy UserNewCustomRepository userNewCustomRepository, @Lazy UserNewRepository userRepository) {
        this.emailService = emailService;
        this.fileApprovedService = fileApprovedService;
        this.fileService = fileService;
        this.passwordEncoder = passwordEncoder;
        this.userNewCustomRepository = userNewCustomRepository;
        this.userRepository = userRepository;
    }
    */

    @Transactional
    @Override
    public Optional<User> findById(UUID userId) {

        return Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with userId: " + userId)));
    }

    @Transactional
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public Page<User> searchPage(UserSearchDTO userSearchDTO, Pageable pageable) {
        return userCustomRepository.searchPage(userSearchDTO, pageable);
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

                    if (user.getFile().getFileId() == null) {

                        if (existingUser.getFile() != null) {
                            fileService.delete(existingUser.getFile().getFileId());
                        }

                        fileService.save(user.getFile());

                    } else {
                        fileService.update(user.getFile().getFileId(), user.getFile());
                    }

                    UserMapper.INSTANCE.update(user, existingUser);

                    existingUser.setModifiedDate(Instant.now());

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

    @Transactional
    @Override
    public User registerNewUser(final SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
        if (signUpRequest.getUserID() != null && userRepository.existsById(signUpRequest.getUserID())) {
            throw new UserAlreadyExistAuthenticationException("User with User id " + signUpRequest.getUserID() + " already exist");
        } else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
        }
        User user = buildUser(signUpRequest);
        user = userRepository.save(user);
        userRepository.flush();
        return user;
    }

    private User buildUser(final SignUpRequest formDTO) {
        User user = new User();
        user.setDisplayName(formDTO.getDisplayName());
        user.setEmail(formDTO.getEmail());
        user.setPassword(passwordEncoder.encode(formDTO.getPassword()));
        user.setCreatedDate(Instant.now());
        user.setProvider(formDTO.getSocialProvider().getProviderType());
        user.setEnabled(Boolean.TRUE);
        user.setProviderUserId(formDTO.getProviderUserId());
        user.setImageURL(formDTO.getUrlImage());

        final HashSet<Role> roles = new HashSet<Role>();
        roles.add(roleService.findByRoleName("ROLE_USER").orElseThrow(() -> new RuntimeException("User Role not found")));
        user.setRoles(roles);

        // Save the user first to generate the userId
        user = userRepository.save(user);

        // Fetch the role and create UserRole association
		/*
		Role userRole = roleService.findByRoleName("ROLE_USER").orElseThrow(() -> new RuntimeException("User Role not found"));
		UserRole userUserRole = new UserRole();
		userUserRole.setUserRoleId(new UserRoleId(user.getUserId(), userRole.getRoleId()));
		userUserRole.setUser(user);
		userUserRole.setRole(userRole);
		userUserRole.setCreatedDate(Instant.now());
		userUserRole.setEnabled(true);

		// Save the UserRole association
		userRoleService.save(userUserRole);
		 */

        return user;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
            throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
        } else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
        User user = findByEmail(oAuth2UserInfo.getEmail()).get();

        if (user != null) {
            if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
                throw new OAuth2AuthenticationProcessingException(
                        "Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
            }

            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(userDetails);
        }

        return LocalUser.create(user, attributes, idToken, userInfo);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setDisplayName(oAuth2UserInfo.getName());
        existingUser.setImageURL(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }

    private SignUpRequest toUserRegistrationObject(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
        return SignUpRequest.builder()
                .providerUserId(oAuth2UserInfo.getId())
                .displayName(oAuth2UserInfo.getName())
                .email(oAuth2UserInfo.getEmail())
                .socialProvider(GeneralUtils.toSocialProvider(registrationId))
                .password("changeit")
                .urlImage(oAuth2UserInfo.getImageUrl())
                .build();
    }
}