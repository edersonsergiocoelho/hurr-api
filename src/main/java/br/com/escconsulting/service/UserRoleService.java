package br.com.escconsulting.service;

import br.com.escconsulting.entity.UserRole;

import java.util.List;
import java.util.UUID;

public interface UserRoleService {
    UserRole findById(UUID id);

    List<UserRole> findAll();

    UserRole save(UserRole review);

    List<UserRole> saveAll(List<UserRole> userRoles);

    UserRole update(UUID id, UserRole userRole);

    void delete(UUID id);
}