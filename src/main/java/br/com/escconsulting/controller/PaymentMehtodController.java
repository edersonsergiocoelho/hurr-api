package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.payment.method.PaymentMethodDTO;
import br.com.escconsulting.dto.payment.method.PaymentMethodSearchDTO;
import br.com.escconsulting.entity.PaymentMethod;
import br.com.escconsulting.mapper.PaymentMethodMapper;
import br.com.escconsulting.service.PaymentMethodService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payment-method")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentMehtodController {

    private final PaymentMethodService paymentMethodService;

    @GetMapping("/{paymentMethodId}")
    public ResponseEntity<PaymentMethodDTO> findById(@PathVariable("paymentMethodId") UUID paymentMethodId) {
        return paymentMethodService.findById(paymentMethodId)
                .map(PaymentMethodMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodDTO>> findAll() {
        return ResponseEntity.ok(
                paymentMethodService.findAll().stream()
                        .map(PaymentMethodMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @CurrentUser LocalUser localUser,
            @RequestBody PaymentMethodSearchDTO paymentMethodSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<PaymentMethodDTO> paymentMethods = paymentMethodService.searchPage(localUser, paymentMethodSearchDTO, pageable);

        return ResponseEntity.ok(paymentMethods);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.save(paymentMethod)
                .map(PaymentMethodMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to save customer bank account."));
    }

    @PutMapping("/{paymentMethodId}")
    public ResponseEntity<?> update(@PathVariable("paymentMethodId") UUID paymentMethodId,
                                    @RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.update(paymentMethodId, paymentMethod)
                .map(PaymentMethodMapper.INSTANCE::toDTO)
                .map(updatedPaymentMethod -> ResponseEntity.ok(updatedPaymentMethod))
                .orElseThrow(() -> new IllegalStateException("Failed to update customer bank account."));
    }

    @DeleteMapping("/{paymentMethodId}")
    public ResponseEntity<?> delete(@PathVariable("paymentMethodId") UUID paymentMethodId) {
        paymentMethodService.delete(paymentMethodId);
        return ResponseEntity.noContent().build();
    }
}