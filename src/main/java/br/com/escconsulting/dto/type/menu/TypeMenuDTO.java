package br.com.escconsulting.dto.type.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeMenuDTO {

    private UUID typeMenuId;
    private String typeMenuName;
    private String description;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}