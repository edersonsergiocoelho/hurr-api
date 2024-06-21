package br.com.escconsulting.config;

import br.com.escconsulting.entity.Role;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.entity.UserRole;
import br.com.escconsulting.entity.UserRoleId;
import br.com.escconsulting.repository.RoleRepository;
import br.com.escconsulting.repository.UserNewRepository;
import br.com.escconsulting.repository.UserRepository;
import br.com.escconsulting.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	private UserNewRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {

		if (alreadySetup) {
			return;
		}

		// Create initial roles
		Role userRole = createRoleIfNotFound("ROLE_USER");
		Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
		Role modRole = createRoleIfNotFound("ROLE_MODERATOR");

		createUserIfNotFound("admin@javachinna.com", Set.of(userRole, adminRole, modRole));

		alreadySetup = true;
	}

	@Transactional
	private User createUserIfNotFound(final String email, Set<Role> roles) {
		User user = userRepository.findByEmail(email).orElse(null);
		if (user == null) {
			user = new User();
			user.setDisplayName("Admin");
			user.setEmail(email);
			user.setPassword(passwordEncoder.encode("admin@"));
			user.setProvider("LOCAL");
			user.setEnabled(true);
			user.setCreatedDate(Instant.now());
			user.setModifiedDate(Instant.now());

			user = userRepository.save(user);

			for (Role role : roles) {
				UserRole userRole = new UserRole();
				userRole.setUserRoleId(new UserRoleId(user.getUserId(), role.getRoleId()));
				userRole.setUser(user);
				userRole.setRole(role);
				userRole.setCreatedDate(Instant.now());
				userRole.setEnabled(true);

				userRoleRepository.save(userRole);
			}
		}
		return user;
	}

	@Transactional
	private Role createRoleIfNotFound(final String roleName) {
		return roleRepository.findByRoleName(roleName).orElseGet(() -> {
			Role role = new Role();
			role.setRoleName(roleName);
			role.setCreatedDate(Instant.now());
			role.setEnabled(true);
			return roleRepository.save(role);
		});
	}
}