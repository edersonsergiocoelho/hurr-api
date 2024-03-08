package br.com.escconsulting.controller;

import br.com.escconsulting.dto.country.CountrySearchDTO;
import br.com.escconsulting.entity.Country;
import br.com.escconsulting.service.CountryService;
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
@RequestMapping("/country")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/{countryId}")
    public ResponseEntity<Country> findById(@PathVariable("countryId") UUID countryId) {
        return countryService.findById(countryId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<Country>> findAll() {
        List<Country> listCountry = countryService.findAll();
        return ResponseEntity.ok(listCountry);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody CountrySearchDTO countrySearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<Country> countries = countryService.searchPage(countrySearchDTO, pageable);

        return ResponseEntity.ok(countries);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Country country) {
        return countryService.save(country)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save country."));
    }

    @PutMapping("/{countryId}")
    public ResponseEntity<?> update(@PathVariable("countryId") UUID countryId,
                                    @RequestBody Country country) {
        return countryService.update(countryId, country)
                .map(updatedCountry -> ResponseEntity.ok(updatedCountry))
                .orElseThrow(() -> new IllegalStateException("Failed to update country."));
    }

    @DeleteMapping("/{countryId}")
    public ResponseEntity<?> delete(@PathVariable("countryId") UUID countryId) {
        countryService.delete(countryId);
        return ResponseEntity.noContent().build();
    }
}