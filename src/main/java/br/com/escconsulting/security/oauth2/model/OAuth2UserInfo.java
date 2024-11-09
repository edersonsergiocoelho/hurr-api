package br.com.escconsulting.security.oauth2.model;

import lombok.Getter;

import java.util.Map;

/**
 *
 * @author Ederson Sergio Monteiro Coelho
 *
 */
@Getter
public abstract class OAuth2UserInfo {

	protected Map<String, Object> attributes;

	public OAuth2UserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

    public abstract String getId();

	public abstract String getName();

	public abstract String getEmail();

	public abstract String getImageUrl();
}