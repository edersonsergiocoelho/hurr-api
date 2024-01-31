package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.SignUpRequest;
import br.com.escconsulting.dto.SocialProvider;
import br.com.escconsulting.entity.*;
import br.com.escconsulting.entity.enumeration.FileTable;
import br.com.escconsulting.entity.enumeration.FileType;
import br.com.escconsulting.exception.OAuth2AuthenticationProcessingException;
import br.com.escconsulting.exception.UserAlreadyExistAuthenticationException;
import br.com.escconsulting.repository.RoleRepository;
import br.com.escconsulting.repository.UserRepository;
import br.com.escconsulting.security.oauth2.user.OAuth2UserInfo;
import br.com.escconsulting.security.oauth2.user.OAuth2UserInfoFactory;
import br.com.escconsulting.service.FileApprovedService;
import br.com.escconsulting.service.FileService;
import br.com.escconsulting.service.UserRoleService;
import br.com.escconsulting.service.UserService;
import br.com.escconsulting.util.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final RoleRepository roleRepository;

	private final UserRoleService userRoleService;

	private final FileService fileService;

	private final FileApprovedService fileApprovedService;

	private final PasswordEncoder passwordEncoder;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(value = "transactionManager")
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

		Role userRole = roleRepository.findByRoleName(Role.ROLE_USER).get();
		UserRole userUserRole = new UserRole();
		userUserRole.setUser(user);
		userUserRole.setRole(userRole);
		userUserRole.setCreatedDate(Instant.now());
		userUserRole.setEnabled(true);

		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(userUserRole);

		user.setRoles(Set.of(userRole));
		user.setUserRoles(userRoles);

		user.setProvider(formDTO.getSocialProvider().getProviderType());
		user.setEnabled(Boolean.TRUE);
		user.setProviderUserId(formDTO.getProviderUserId());
		user.setImageURL(formDTO.getUrlImage());
		return user;
	}

	@Override
	public User findUserByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
		if (StringUtils.isEmpty(oAuth2UserInfo.getName())) {
			throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
		} else if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}
		SignUpRequest userDetails = toUserRegistrationObject(registrationId, oAuth2UserInfo);
		User user = findUserByEmail(oAuth2UserInfo.getEmail());

		if (user != null) {
			if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(SocialProvider.LOCAL.getProviderType())) {
				throw new OAuth2AuthenticationProcessingException(
						"Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
			}

			List<UserRole> all = userRoleService.findAll();

			Set<Role> roleSet = all.stream()
					.map(UserRole::getRole)
					.collect(Collectors.toSet());

			user.setRoles(roleSet);

			user = updateExistingUser(user, oAuth2UserInfo);
		} else {
			user = registerNewUser(userDetails);

			for (UserRole userRole: user.getUserRoles().stream().toList()) {
				UserRoleId id = new UserRoleId();
				id.setRoleId(userRole.getRole().getRoleId());
				id.setUserId(userRole.getUser().getUserId());

				userRole.setUserRoleId(id);
			}

			userRoleService.saveAll(user.getUserRoles().stream().toList());
		}

		return LocalUser.create(user, attributes, idToken, userInfo);
	}

	private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
		existingUser.setDisplayName(oAuth2UserInfo.getName());
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

	@Override
	public Optional<User> findUserById(UUID id) {
		return userRepository.findById(id);
	}

	@Override
	public void uploadHandler(LocalUser localUser, MultipartFile[] files) throws IOException {

		MultipartFile multipartFile = Arrays.stream(files).findFirst().get();

		File file = new File();
		file.setContentType(multipartFile.getContentType());
		file.setOriginalFileName(multipartFile.getOriginalFilename());
		file.setDataAsByteArray(multipartFile.getBytes());
		file.setCreatedDate(Instant.now());
		file.setEnabled(Boolean.FALSE);

		file = fileService.save(file);

		User userFindByEmail = userRepository.findByEmail(localUser.getUser().getEmail());

		userFindByEmail.setPhotoFileId(file.getFileId());

		userRepository.save(userFindByEmail);

		FileApproved fileApproved = new FileApproved();

		fileApproved.setFileType(FileType.PROFILE_PICTURE);
		fileApproved.setFileTable(FileTable.USER);
		fileApproved.setFileId(file.getFileId());
		fileApproved.setCreatedBy(localUser.getUser().getUserId());
		fileApproved.setCreatedDate(Instant.now());
		fileApproved.setEnabled(Boolean.TRUE);

		fileApprovedService.save(fileApproved);
	}
}
