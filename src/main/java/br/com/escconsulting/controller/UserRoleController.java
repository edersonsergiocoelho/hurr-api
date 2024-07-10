package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.user.role.UserRoleDTO;
import br.com.escconsulting.dto.user.role.UserRoleSearchDTO;
import br.com.escconsulting.entity.UserRole;
import br.com.escconsulting.mapper.UserRoleMapper;
import br.com.escconsulting.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRoleController {

    private final UserRoleService userRoleService;

    @GetMapping("/{userId}/{roleId}")
    public ResponseEntity<?> findById(@PathVariable("userId") UUID userId,
                                      @PathVariable("roleId") UUID roleId) {
        return userRoleService.findById(userId, roleId)
                .map(UserRoleMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                userRoleService.findAll().stream()
                        .map(UserRoleMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(@RequestBody UserRoleSearchDTO userRoleSearchDTO,
                                    @RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                    @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
                                    @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<UserRoleDTO> userRoles = userRoleService.searchPage(userRoleSearchDTO, pageable);

        return ResponseEntity.ok(userRoles);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserRole userRole) {
        return userRoleService.save(userRole)
                .map(UserRoleMapper.INSTANCE::toDTO)
                .map(savedUserRole -> ResponseEntity.status(HttpStatus.CREATED).build())
                .orElseThrow(() -> new IllegalStateException("Failed to save user role."));
    }

    @PutMapping("/{userId}/{roleId}")
    public ResponseEntity<?> update(@PathVariable("userId") UUID userId,
                                    @PathVariable("roleId") UUID roleId,
                                    @RequestBody UserRole userRole) {
        return userRoleService.update(userId, roleId, userRole)
                .map(UserRoleMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update user role."));
    }

    @DeleteMapping("/{userId}/{roleId}")
    public ResponseEntity<?> delete(@PathVariable("userId") UUID userId,
                                    @PathVariable("roleId") UUID roleId) {
        userRoleService.delete(userId, roleId);
        return ResponseEntity.noContent().build();
    }
}