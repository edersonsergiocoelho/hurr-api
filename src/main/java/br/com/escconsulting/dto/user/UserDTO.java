package br.com.escconsulting.dto.user;

import br.com.escconsulting.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID userId;
    private String providerUserId;
    private String email;
    private String displayName;
    private String password;
    private String forgotPasswordVerificationCode;
    private Boolean forgotPasswordValidated = false;
    private String provider;
    private UUID photoFileId;
    private Boolean photoValidated = false;
    private String imageURL;
    private Set<Role> roles;
}