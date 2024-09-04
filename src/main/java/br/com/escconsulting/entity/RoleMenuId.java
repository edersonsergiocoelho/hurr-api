package br.com.escconsulting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class RoleMenuId implements Serializable {

    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "menu_id")
    private UUID menuId;
}