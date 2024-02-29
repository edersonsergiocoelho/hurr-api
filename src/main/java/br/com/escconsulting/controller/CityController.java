package br.com.escconsulting.controller;

import br.com.escconsulting.dto.city.CitySearchDTO;
import br.com.escconsulting.entity.City;
import br.com.escconsulting.service.CityService;
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
@RequestMapping("/city")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CityController {

    private final CityService cityService;

    @GetMapping("/{cityId}")
    public ResponseEntity<City> findById(@PathVariable("cityId") UUID cityId) {
        return cityService.findById(cityId)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/by/stateId/{stateId}")
    public ResponseEntity<List<City>> findByStateId(@PathVariable("stateId") UUID stateId) {
        List<City> listCity = cityService.findByStateId(stateId);
        return ResponseEntity.ok(listCity);
    }

    @GetMapping
    public ResponseEntity<List<City>> findAll() {
        List<City> listCity = cityService.findAll();
        return ResponseEntity.ok(listCity);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody CitySearchDTO citySearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "ASC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<City> countries = cityService.searchPage(citySearchDTO, pageable);

        return ResponseEntity.ok(countries);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody City city) {
        return cityService.save(city)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save city."));
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<?> update(@PathVariable("cityId") UUID cityId,
                                    @RequestBody City city) {
        return cityService.update(cityId, city)
                .map(updatedCity -> ResponseEntity.ok(updatedCity))
                .orElseThrow(() -> new IllegalStateException("Failed to update city."));
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<?> delete(@PathVariable("cityId") UUID cityId) {
        cityService.delete(cityId);
        return ResponseEntity.noContent().build();
    }
}