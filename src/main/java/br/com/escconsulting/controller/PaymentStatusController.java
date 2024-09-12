package br.com.escconsulting.controller;

import br.com.escconsulting.dto.payment.status.PaymentStatusDTO;
import br.com.escconsulting.dto.payment.status.PaymentStatusSearchDTO;
import br.com.escconsulting.entity.PaymentStatus;
import br.com.escconsulting.mapper.PaymentStatusMapper;
import br.com.escconsulting.service.PaymentStatusService;
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
@RequestMapping("/payment-status")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentStatusController {

    private final PaymentStatusService paymentStatusService;

    @GetMapping("/{paymentStatusId}")
    public ResponseEntity<?> findById(@PathVariable("paymentStatusId") UUID paymentStatusId) {
        return paymentStatusService.findById(paymentStatusId)
                .map(PaymentStatusMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/by/payment-status-name/{paymentStatusName}")
    public ResponseEntity<?> findByPaymentStatusName(@PathVariable("paymentStatusName") String paymentStatusName) {
        return paymentStatusService.findByPaymentStatusName(paymentStatusName)
                .map(PaymentStatusMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                paymentStatusService.findAll().stream()
                        .map(PaymentStatusMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @RequestBody PaymentStatusSearchDTO paymentStatusSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<PaymentStatusDTO> paymentStatuses = paymentStatusService.searchPage(paymentStatusSearchDTO, pageable);

        return ResponseEntity.ok(paymentStatuses);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PaymentStatus paymentStatus) {
        return paymentStatusService.save(paymentStatus)
                .map(PaymentStatusMapper.INSTANCE::toDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseThrow(() -> new IllegalStateException("Failed to save payment method."));
    }

    @PutMapping("/{paymentStatusId}")
    public ResponseEntity<?> update(@PathVariable("paymentStatusId") UUID paymentStatusId,
                                    @RequestBody PaymentStatus paymentStatus) {
        return paymentStatusService.update(paymentStatusId, paymentStatus)
                .map(PaymentStatusMapper.INSTANCE::toDTO)
                .map(updatedPaymentStatus -> ResponseEntity.ok(updatedPaymentStatus))
                .orElseThrow(() -> new IllegalStateException("Failed to update payment method."));
    }

    @DeleteMapping("/{paymentStatusId}")
    public ResponseEntity<?> delete(@PathVariable("paymentStatusId") UUID paymentStatusId) {
        paymentStatusService.delete(paymentStatusId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll(@RequestBody List<UUID> paymentStatusIds) {
        paymentStatusService.deleteAll(paymentStatusIds);
        return ResponseEntity.noContent().build();
    }
}