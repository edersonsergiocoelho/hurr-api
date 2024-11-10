package br.com.escconsulting.dto.user.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleIdDTO {

    private UUID userId;
    private UUID roleId;
}