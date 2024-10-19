package br.com.escconsulting.dto;

import lombok.Value;

@Value
public class JWTAuthenticationResponse {

	private String accessToken;
	private UserInfo user;
}