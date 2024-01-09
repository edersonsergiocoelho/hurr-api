package br.com.escconsulting.dto;

import lombok.Value;

import java.util.List;

@Value
public class UserInfo {
	private String id, displayName, email, urlImage;
	private List<String> roles;
}