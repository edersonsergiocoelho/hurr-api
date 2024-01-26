package br.com.escconsulting.controller;

import br.com.escconsulting.entity.Role;
import br.com.escconsulting.service.RoleService;
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
@RequestMapping("/role")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") UUID id) {
        return roleService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestParam(value = "roleName", required = false) String roleName,
            @RequestParam(value = "enabled", required = false) Boolean enabled,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "roleName") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<Role> roles = roleService.searchPage(roleName, enabled, pageable);

        return ResponseEntity.ok(roles);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Role role) {
        return roleService.save(role)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save role"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody Role role) {
        return roleService.update(id, role)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update role"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}