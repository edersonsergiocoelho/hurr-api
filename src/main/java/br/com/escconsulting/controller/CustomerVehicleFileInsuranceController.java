package br.com.escconsulting.controller;

import br.com.escconsulting.config.CurrentUser;
import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.file.insurance.CustomerVehicleFileInsuranceDTO;
import br.com.escconsulting.dto.customer.vehicle.file.insurance.CustomerVehicleFileInsuranceSearchDTO;
import br.com.escconsulting.entity.CustomerVehicleFileInsurance;
import br.com.escconsulting.mapper.CustomerVehicleFileInsuranceMapper;
import br.com.escconsulting.service.CustomerVehicleFileInsuranceService;
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
@RequestMapping("/customer-vehicle-file-insurance")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleFileInsuranceController {

    private final CustomerVehicleFileInsuranceService customerVehicleFileInsuranceService;

    @GetMapping("/{customerVehicleFileInsuranceId}")
    public ResponseEntity<?> findById(@PathVariable("customerVehicleFileInsuranceId") UUID customerVehicleFileInsuranceId) {
        return customerVehicleFileInsuranceService.findById(customerVehicleFileInsuranceId)
                .map(CustomerVehicleFileInsuranceMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                customerVehicleFileInsuranceService.findAll().stream()
                        .map(CustomerVehicleFileInsuranceMapper.INSTANCE::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/search/page")
    public ResponseEntity<?> search(
            @CurrentUser LocalUser localUser,
            @RequestBody CustomerVehicleFileInsuranceSearchDTO customerVehicleFileInsuranceSearchDTO,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortDir", defaultValue = "DESC") String sortDir,
            @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));

        Page<CustomerVehicleFileInsuranceDTO> customerVehicleFileInsurances = customerVehicleFileInsuranceService.searchPage(localUser, customerVehicleFileInsuranceSearchDTO, pageable);

        return ResponseEntity.ok(customerVehicleFileInsurances);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerVehicleFileInsurance customerVehicleFileInsurance) {
        return customerVehicleFileInsuranceService.save(customerVehicleFileInsurance)
                .map(CustomerVehicleFileInsuranceMapper.INSTANCE::toDTO)
                .map(savedCustomerVehicleFileInsurance -> ResponseEntity.status(HttpStatus.CREATED).build())
                .orElseThrow(() -> new IllegalStateException("Failed to save customer withdrawal request."));
    }

    @PostMapping("/all")
    public ResponseEntity<?> saveBulk(@RequestBody List<CustomerVehicleFileInsurance> customerVehicleFileInsurances) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                customerVehicleFileInsuranceService.saveAll(customerVehicleFileInsurances).stream()
                .map(CustomerVehicleFileInsuranceMapper.INSTANCE::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{customerVehicleFileInsuranceId}")
    public ResponseEntity<?> update(@PathVariable("customerVehicleFileInsuranceId") UUID customerVehicleFileInsuranceId,
                                    @RequestBody CustomerVehicleFileInsurance customerVehicleFileInsurance) {
        return customerVehicleFileInsuranceService.update(customerVehicleFileInsuranceId, customerVehicleFileInsurance)
                .map(CustomerVehicleFileInsuranceMapper.INSTANCE::toDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new IllegalStateException("Failed to update customer withdrawal request."));
    }

    @DeleteMapping("/{customerVehicleFileInsuranceId}")
    public ResponseEntity<?> delete(@PathVariable("customerVehicleFileInsuranceId") UUID customerVehicleFileInsuranceId) {
        customerVehicleFileInsuranceService.delete(customerVehicleFileInsuranceId);
        return ResponseEntity.noContent().build();
    }
}