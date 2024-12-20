package br.com.escconsulting.util;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.SocialProvider;
import br.com.escconsulting.dto.UserInfo;
import br.com.escconsulting.entity.Role;
import br.com.escconsulting.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Ederson Sergio Monteiro Coelho
 *
 */
public class GeneralUtils {

	public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return authorities;
	}

	public static SocialProvider toSocialProvider(String providerId) {
		for (SocialProvider socialProvider : SocialProvider.values()) {
			if (socialProvider.getProviderType().equals(providerId)) {
				return socialProvider;
			}
		}
		return SocialProvider.LOCAL;
	}

	public static UserInfo buildUserInfo(LocalUser localUser) {
		List<String> roles = localUser.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
		User user = localUser.getUser();
		String photoFileId = (user.getPhotoFileId() != null) ? user.getPhotoFileId().toString() : null;
		return new UserInfo(user.getUserId().toString(), user.getDisplayName(), user.getEmail(), user.getImageURL(), photoFileId, user.getPhotoValidated(), roles);
	}
}
