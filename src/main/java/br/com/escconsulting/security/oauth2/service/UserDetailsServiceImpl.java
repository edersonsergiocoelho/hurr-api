package br.com.escconsulting.security.oauth2.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.exception.ResourceNotFoundException;
import br.com.escconsulting.service.UserService;
import br.com.escconsulting.util.GeneralUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 *
 * @author Ederson Sergio Monteiro Coelho
 *
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	@Override
	@Transactional
	public LocalUser loadUserByUsername(final String email) throws UsernameNotFoundException {
		User user = userService.findByEmail(email).get();
		if (user == null) {
			throw new UsernameNotFoundException("User " + email + " was not found in the database");
		}

		return createLocalUser(user);
	}

	@Transactional
	public LocalUser loadUserById(UUID id) {
		User user = userService.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

		return createLocalUser(user);
	}

	/**
	 * @param user
	 * @return
	 */
	private LocalUser createLocalUser(User user) {
		return new LocalUser(user.getEmail(), user.getPassword(), user.getEnabled(), true, true, true, GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()), user);
	}
}
