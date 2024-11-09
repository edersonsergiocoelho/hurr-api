package br.com.escconsulting.config;

import br.com.escconsulting.entity.Role;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.entity.UserRole;
import br.com.escconsulting.entity.UserRoleId;
import br.com.escconsulting.repository.RoleRepository;
import br.com.escconsulting.repository.UserRepository;
import br.com.escconsulting.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Set;

@Component
@Slf4j
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Value("${scripts.test.enabled}")
	private boolean scriptsTestEnabled;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {

		if (!scriptsTestEnabled) {
			return;
		}

		// Criar roles iniciais se não existirem
		Role adminRole = getRoleIfFound("ROLE_ADMIN");
		Role customerVehicleRole = getRoleIfFound("ROLE_CUSTOMER_VEHICLE");
		Role modRole = getRoleIfFound("ROLE_MODERATOR");
		Role userRole = getRoleIfFound("ROLE_USER");

		// Criar usuários iniciais
		createUserIfNotFound("admin@hurr.com.br", "Admin", "admin@", Set.of(adminRole));

		if (scriptsTestEnabled) {
			createUserIfNotFound("joao.silva@example.com", "João Silva", "1234", Set.of(customerVehicleRole));
			createUserIfNotFound("maria.oliveira@example.com", "Maria Oliveira", "1234", Set.of(modRole));
			createUserIfNotFound("carlos.souza@example.com", "Carlos Souza", "1234", Set.of(userRole));
			createUserIfNotFound("ana.costa@example.com", "Ana Costa", "1234", Set.of(customerVehicleRole));
			createUserIfNotFound("luiz.pereira@example.com", "Luiz Pereira", "1234", Set.of(userRole));
			createUserIfNotFound("juliana.santos@example.com", "Juliana Santos", "1234", Set.of(userRole));
			createUserIfNotFound("fernando.lima@example.com", "Fernando Lima", "1234", Set.of(customerVehicleRole));
			createUserIfNotFound("gabriela.rocha@example.com", "Gabriela Rocha", "1234", Set.of(userRole));
			createUserIfNotFound("pedro.almeida@example.com", "Pedro Almeida", "1234", Set.of(userRole));
			createUserIfNotFound("renata.mendes@example.com", "Renata Mendes", "1234", Set.of(userRole));
		}
	}

	@Transactional
	private User createUserIfNotFound(final String email, String displayName, String password, Set<Role> roles) {
		User user = userRepository.findByEmail(email).orElse(null);
		if (user == null) {
			user = new User();
			user.setDisplayName(displayName);
			user.setEmail(email);
			user.setPassword(passwordEncoder.encode(password));
			user.setProvider("LOCAL");
			user.setEnabled(true);
			user.setCreatedDate(Instant.now());
			user.setModifiedDate(Instant.now());

			if (!email.equalsIgnoreCase("admin@hurr.com.br") &&
					!email.equalsIgnoreCase("renata.mendes@example.com")) {
				user.setPhotoValidated(Boolean.TRUE);
			}

			user = userRepository.save(user);

			for (Role role : roles) {
				UserRole userRole = new UserRole();
				userRole.setId(new UserRoleId(user.getUserId(), role.getRoleId()));
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
	private Role getRoleIfFound(final String roleName) {
		return roleRepository.findByRoleName(roleName)
				.orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
	}
}