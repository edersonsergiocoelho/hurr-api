package br.com.escconsulting.dto.role.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuIdDTO {

    private UUID roleId;
    private UUID menuId;
}