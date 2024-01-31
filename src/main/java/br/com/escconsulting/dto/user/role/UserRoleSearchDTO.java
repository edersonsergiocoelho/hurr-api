package br.com.escconsulting.dto.user.role;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserRoleSearchDTO {

    private String displayName;

    private String email;

    private UUID roleId;

    private Boolean enabled;
}