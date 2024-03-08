package br.com.escconsulting.controller;

import br.com.escconsulting.dto.state.StateSearchDTO;
import br.com.escconsulting.entity.State;
import br.com.escconsulting.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/state")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StateController {

    private final StateService stateService;

    @GetMapping("/{stateId}")
    public ResponseEntity<State> findById(@PathVariable("stateId") UUID stateId) {
        return stateService.findById(stateId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/by/countryId/{countryId}")
    public ResponseEntity<List<State>> findByCountryId(@PathVariable("countryId") UUID countryId) {
        List<State> listState = stateService.findByCountryId(countryId);
        return ResponseEntity.ok(listState);
    }

    @GetMapping
    public ResponseEntity<List<State>> findAll() {
        List<State> listState = stateService.findAll();
        return ResponseEntity.ok(listState);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody StateSearchDTO stateSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<State> countries = stateService.searchPage(stateSearchDTO, pageable);

        return ResponseEntity.ok(countries);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody State state) {
        return stateService.save(state)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save state."));
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<?> update(@PathVariable("stateId") UUID stateId,
                                    @RequestBody State state) {
        return stateService.update(stateId, state)
                .map(updatedState -> ResponseEntity.ok(updatedState))
                .orElseThrow(() -> new IllegalStateException("Failed to update state."));
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<?> delete(@PathVariable("stateId") UUID stateId) {
        stateService.delete(stateId);
        return ResponseEntity.noContent().build();
    }
}