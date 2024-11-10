package br.com.escconsulting.dto.role;

import br.com.escconsulting.dto.user.UserDTO;
import br.com.escconsulting.entity.RoleMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private UUID roleId;
    private String roleName;
    private Set<UserDTO> users;
    private Set<RoleMenu> roleMenus;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}