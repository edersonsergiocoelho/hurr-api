package br.com.escconsulting.dto.user.role;

import br.com.escconsulting.dto.role.RoleDTO;
import br.com.escconsulting.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO {

    private UserRoleIdDTO id;
    private UserDTO user;
    private RoleDTO role;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}