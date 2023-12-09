package br.com.escconsulting.config;

import br.com.escconsulting.dto.SocialProvider;
import br.com.escconsulting.model.Role;
import br.com.escconsulting.model.User;
import br.com.escconsulting.repository.RoleRepository;
import br.com.escconsulting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {

		if (alreadySetup) {
			return;
		}
		// Create initial roles
		Role userRole = createRoleIfNotFound(Role.ROLE_USER);
		Role adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);
		Role modRole = createRoleIfNotFound(Role.ROLE_MODERATOR);
		/*
		createUserIfNotFound("admin@javachinna.com", Set.of(userRole, adminRole, modRole));
		alreadySetup = true;
		*/
	}

	@Transactional
	private final User createUserIfNotFound(final String email, Set<Role> roles) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			user = new User();
			user.setDisplayName("Admin");
			user.setEmail(email);
			user.setPassword(passwordEncoder.encode("admin@"));
			user.setRoles(roles);
			user.setProvider(SocialProvider.LOCAL.getProviderType());
			user.setEnabled(true);
			Date now = Calendar.getInstance().getTime();
			user.setCreatedDate(now);
			user.setModifiedDate(now);
			user = userRepository.save(user);
		}
		return user;
	}

	@Transactional
	private Role createRoleIfNotFound(final String name) {
		return roleRepository.findByName(name)
				.orElseGet(() -> {
					Role role = new Role();
					role.setName(name);
					role.setCreatedDate(Instant.now());
					role.setEnabled(Boolean.TRUE);
					return role;
				});
	}
}