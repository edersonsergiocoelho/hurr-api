package br.com.escconsulting.controller;

import br.com.escconsulting.dto.bank.BankDTO;
import br.com.escconsulting.dto.bank.BankSearchDTO;
import br.com.escconsulting.entity.Bank;
import br.com.escconsulting.mapper.BankMapper;
import br.com.escconsulting.service.BankService;
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

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BankController {

    private final BankService bankService;

    @GetMapping("/{bankId}")
    public ResponseEntity<BankDTO> findById(@PathVariable("bankId") UUID bankId) {
        return bankService.findById(bankId)
                .map(BankMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<Bank>> findAll() {
        List<Bank> listBank = bankService.findAll();
        return ResponseEntity.ok(listBank);
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody BankSearchDTO bankSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<BankDTO> banks = bankService.searchPage(bankSearchDTO, pageable);

        return ResponseEntity.ok(banks);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Bank bank) {
        return bankService.save(bank)
                .map(BankMapper.INSTANCE::toDTO)
                .map(savedBank -> ResponseEntity.status(HttpStatus.CREATED).body(savedBank))
                .orElseThrow(() -> new IllegalStateException("Failed to save bank."));
    }

    @PutMapping("/{bankId}")
    public ResponseEntity<?> update(@PathVariable("bankId") UUID bankId,
                                    @RequestBody Bank bank) {
        return bankService.update(bankId, bank)
                .map(BankMapper.INSTANCE::toDTO)
                .map(updatedBank -> ResponseEntity.ok(updatedBank))
                .orElseThrow(() -> new IllegalStateException("Failed to update bank."));
    }

    @DeleteMapping("/{bankId}")
    public ResponseEntity<?> delete(@PathVariable("bankId") UUID bankId) {
        bankService.delete(bankId);
        return ResponseEntity.noContent().build();
    }
}