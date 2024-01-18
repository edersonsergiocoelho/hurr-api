package br.com.escconsulting.dto;

import lombok.Value;

import java.util.List;

@Value
public class UserInfo {
	private String id, displayName, email, imageURL, fileId;
	private Boolean photoValidated;
	private List<String> roles;
}