package br.com.escconsulting.dto;

import lombok.Value;

import java.util.List;

@Value
public class UserInfo {
	private String userId, displayName, email, imageURL, photoFileId;
	private Boolean photoValidated;
	private List<String> roles;
}