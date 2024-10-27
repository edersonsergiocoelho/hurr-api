package br.com.escconsulting.controller;

import br.com.escconsulting.dto.fee.FeeDTO;
import br.com.escconsulting.dto.fee.FeeSearchDTO;
import br.com.escconsulting.entity.Fee;
import br.com.escconsulting.mapper.FeeMapper;
import br.com.escconsulting.service.FeeService;
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
@RequestMapping("/fee")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeeController {

    private final FeeService feeService;

    @GetMapping("/{feeId}")
    public ResponseEntity<?> findById(@PathVariable("feeId") UUID feeId) {
        return feeService.findById(feeId)
                .map(FeeMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    /*
    @GetMapping("/by/payment-status-name/{feeName}")
    public ResponseEntity<?> findByFeeName(@PathVariable("feeName") String feeName) {
        return feeService.findByFeeName(feeName)
                .map(FeeMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
    */

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                feeService.findAll().stream()
                        .map(FeeMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody FeeSearchDTO feeSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<FeeDTO> feees = feeService.searchPage(feeSearchDTO, pageable);

        return ResponseEntity.ok(feees);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Fee fee) {
        return feeService.save(fee)
                .map(FeeMapper.INSTANCE::toDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseThrow(() -> new IllegalStateException("Failed to save payment status."));
    }

    @PutMapping("/{feeId}")
    public ResponseEntity<?> update(@PathVariable("feeId") UUID feeId,
                                    @RequestBody Fee fee) {
        return feeService.update(feeId, fee)
                .map(FeeMapper.INSTANCE::toDTO)
                .map(updatedFee -> ResponseEntity.ok(updatedFee))
                .orElseThrow(() -> new IllegalStateException("Failed to update payment status."));
    }

    @DeleteMapping("/{feeId}")
    public ResponseEntity<?> delete(@PathVariable("feeId") UUID feeId) {
        feeService.delete(feeId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll(@RequestBody List<UUID> feeIds) {
        feeService.deleteAll(feeIds);
        return ResponseEntity.noContent().build();
    }
}