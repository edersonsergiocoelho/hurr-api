package br.com.escconsulting.controller;

import br.com.escconsulting.dto.user.role.UserRoleSearchDTO;
import br.com.escconsulting.entity.UserRole;
import br.com.escconsulting.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user-role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserRoleController {

    private final UserRoleService userRoleService;

    @GetMapping("/{userId}/{roleId}")
    public ResponseEntity<?> findById(@PathVariable("userId") UUID userId,
                                      @PathVariable("roleId") UUID roleId) {
        return userRoleService.findById(userId, roleId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userRoleService.findAll());
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody UserRoleSearchDTO userRoleSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "user.displayName") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<UserRole> userRoles = userRoleService.searchPage(userRoleSearchDTO, pageable);

        return ResponseEntity.ok(userRoles);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserRole userRole) {
        return userRoleService.save(userRole)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save user role"));
    }

    @PutMapping("/{userId}/{roleId}")
    public ResponseEntity<?> update(@PathVariable("userId") UUID userId,
                                    @PathVariable("roleId") UUID roleId,
                                    @RequestBody UserRole userRole) {
        return userRoleService.update(userId, roleId, userRole)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update user role"));
    }

    @DeleteMapping("/{userId}/{roleId}")
    public ResponseEntity<?> delete(@PathVariable("userId") UUID userId,
                                    @PathVariable("roleId") UUID roleId) {
        userRoleService.delete(userId, roleId);
        return ResponseEntity.noContent().build();
    }
}