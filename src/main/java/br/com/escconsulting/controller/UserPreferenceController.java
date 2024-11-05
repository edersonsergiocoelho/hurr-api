package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.use.preference.UserPreferenceDTO;
import br.com.escconsulting.dto.use.preference.UserPreferenceSearchDTO;
import br.com.escconsulting.entity.UserPreference;
import br.com.escconsulting.mapper.UserPreferenceMapper;
import br.com.escconsulting.service.UserPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-preference")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserPreferenceController {

    private final UserPreferenceService userPreferenceService;

    @GetMapping("/{userPreferenceId}")
    public ResponseEntity<?> findById(@PathVariable("userPreferenceId") UUID userPreferenceId) {
        return userPreferenceService.findById(userPreferenceId)
                .map(UserPreferenceMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }

    @GetMapping("/by/user/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable("userId") UUID userId) {
        return userPreferenceService.findByUserId(userId)
                .map(UserPreferenceMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.noContent()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                userPreferenceService.findAll().stream()
                        .map(UserPreferenceMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody UserPreferenceSearchDTO userPreferenceSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<UserPreferenceDTO> userPreferencees = userPreferenceService.searchPage(userPreferenceSearchDTO, pageable);

        return ResponseEntity.ok(userPreferencees);
    }

    @PostMapping
    public ResponseEntity<?> save(@CurrentUser LocalUser user,
                                  @RequestBody UserPreference userPreference) {
        return userPreferenceService.save(user, userPreference)
                .map(UserPreferenceMapper.INSTANCE::toDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseThrow(() -> new IllegalStateException("Failed to save payment status."));
    }

    @PutMapping("/{userPreferenceId}")
    public ResponseEntity<?> update(@CurrentUser LocalUser user,
                                    @PathVariable("userPreferenceId") UUID userPreferenceId,
                                    @RequestBody UserPreference userPreference) {
        return userPreferenceService.update(userPreferenceId, userPreference)
                .map(UserPreferenceMapper.INSTANCE::toDTO)
                .map(updatedUserPreference -> ResponseEntity.ok(updatedUserPreference))
                .orElseThrow(() -> new IllegalStateException("Failed to update payment status."));
    }

    @DeleteMapping("/{userPreferenceId}")
    public ResponseEntity<?> delete(@PathVariable("userPreferenceId") UUID userPreferenceId) {
        userPreferenceService.delete(userPreferenceId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll(@RequestBody List<UUID> userPreferenceIds) {
        userPreferenceService.deleteAll(userPreferenceIds);
        return ResponseEntity.noContent().build();
    }
}