package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.SignUpRequest;
import br.com.escconsulting.dto.user.UserForgotPasswordDTO;
import br.com.escconsulting.dto.user.UserPasswordValidateCodeDTO;
import br.com.escconsulting.dto.user.UserPasswordVerificationCodeDTO;
import br.com.escconsulting.dto.user.UserSearchDTO;
import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.exception.UserAlreadyExistAuthenticationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<User> findById(UUID userId);

    List<User> findAll();

    Optional<User> findByEmail(String email);

    Page<User> searchPage(UserSearchDTO userSearchDTO, Pageable pageable);

    Optional<FileApproved> upload(LocalUser localUser, MultipartFile[] files) throws IOException;

    Optional<User> save(User user);

    Optional<User> forgotPasswordVerificationCode(UserPasswordVerificationCodeDTO userPasswordVerificationCodeDTO);

    Optional<User> forgotPasswordValidated(UserPasswordValidateCodeDTO userPasswordValidateCodeDTO);

    Optional<User> update(UUID userId, User user);

    Optional<User> forgotPassword(UserForgotPasswordDTO userForgotPasswordDTO);

    void delete(UUID userId);

    public User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

    LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);
}