package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.SignUpRequest;
import br.com.escconsulting.entity.FileApproved;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.exception.UserAlreadyExistAuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

	List<User> findAll();

	public User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

	User findUserByEmail(String email);

	Optional<User> findUserById(UUID id);

	LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);

	Optional<FileApproved> uploadHandler(LocalUser localUser, MultipartFile[] files) throws IOException;
}
