package br.com.escconsulting.service;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.entity.Role;
import br.com.escconsulting.entity.UserRole;
import br.com.escconsulting.exception.ResourceNotFoundException;
import br.com.escconsulting.entity.User;
import br.com.escconsulting.util.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 
 * @author Chinna
 *
 */
@Service("localUserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Override
	@Transactional
	public LocalUser loadUserByUsername(final String email) throws UsernameNotFoundException {
		User user = userService.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User " + email + " was not found in the database");
		}

		return createLocalUser(user);
	}

	@Transactional
	public LocalUser loadUserById(UUID id) {
		User user = userService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

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
