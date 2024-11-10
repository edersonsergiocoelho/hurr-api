package br.com.escconsulting.dto.role.menu;

import br.com.escconsulting.entity.Menu;
import br.com.escconsulting.entity.Role;
import br.com.escconsulting.entity.TypeMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuDTO {

    private RoleMenuIdDTO id;
    private Role role;
    private Menu menu;
    private TypeMenu typeMenu;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}