package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.user.UserForgotPasswordDTO;
import br.com.escconsulting.dto.user.UserPasswordValidateCodeDTO;
import br.com.escconsulting.dto.user.UserPasswordVerificationCodeDTO;
import br.com.escconsulting.dto.user.UserSearchDTO;
import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.entity.User;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserNewService {

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
}