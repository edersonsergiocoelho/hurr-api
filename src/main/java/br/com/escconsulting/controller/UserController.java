package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.user.UserForgotPasswordDTO;
import br.com.escconsulting.dto.user.UserPasswordValidateCodeDTO;
import br.com.escconsulting.dto.user.UserPasswordVerificationCodeDTO;
import br.com.escconsulting.dto.user.UserSearchDTO;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.mapper.UserMapper;
import br.com.escconsulting.service.UserService;
import br.com.escconsulting.util.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> findById(@PathVariable("userId") UUID userId) {
        return userService.findById(userId)
                .map(UserMapper.INSTANCE::toDTONoRole)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/by/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email)
                .map(UserMapper.INSTANCE::toSimpleDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                userService.findAll().stream()
                        .map(UserMapper.INSTANCE::toSimpleDTO)
                        .toList()
        );
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@CurrentUser LocalUser user) {
        return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody UserSearchDTO userSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<User> users = userService.searchPage(userSearchDTO, pageable);

        return ResponseEntity.ok(users);
    }

    @PostMapping("upload")
    public ResponseEntity<?> upload(@CurrentUser LocalUser user, @RequestParam("file") MultipartFile[] files) throws IOException {
        return userService.upload(user, files)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to upload profile picture"));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        return userService.save(user)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save user"));
    }

    @PostMapping("forgotPasswordVerificationCode")
    public ResponseEntity<?> forgotPasswordVerificationCode(@RequestBody UserPasswordVerificationCodeDTO userPasswordVerificationCodeDTO) {
        return userService.forgotPasswordVerificationCode(userPasswordVerificationCodeDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to forgot password verification code"));
    }

    @PostMapping("forgotPasswordValidated")
    public ResponseEntity<?> forgotPasswordValidated(@RequestBody UserPasswordValidateCodeDTO userPasswordValidateCodeDTO) {
        return userService.forgotPasswordValidated(userPasswordValidateCodeDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to forgot password validated"));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable("userId") UUID userId,
                                    @RequestBody User user) {
        return userService.update(userId, user)
                .map(UserMapper.INSTANCE::toDTONoRole)
                .map(updatedUser -> ResponseEntity.ok(updatedUser))
                .orElseThrow(() -> new IllegalStateException("Failed to update user"));
    }

    @PutMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestBody UserForgotPasswordDTO userForgotPasswordDTO) {
        return userService.forgotPassword(userForgotPasswordDTO)
                .map(updatedUser -> ResponseEntity.ok(updatedUser))
                .orElseThrow(() -> new IllegalStateException("Failed to update user"));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable("userId") UUID userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}