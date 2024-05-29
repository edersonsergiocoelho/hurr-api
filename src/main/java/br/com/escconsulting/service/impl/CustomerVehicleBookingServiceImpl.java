package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingDTO;
import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingSearchDTO;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.CustomerVehicleBooking;
import br.com.escconsulting.repository.CustomerVehicleBookingRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleBookingCustomRepository;
import br.com.escconsulting.service.CustomerService;
import br.com.escconsulting.service.CustomerVehicleBookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleBookingServiceImpl implements CustomerVehicleBookingService {

    private final CustomerService customerService;

    private final CustomerVehicleBookingRepository customerVehicleBookingRepository;

    private final CustomerVehicleBookingCustomRepository customerVehicleBookingCustomRepository;

    @Transactional
    @Override
    public Optional<CustomerVehicleBooking> findById(UUID customerVehicleBookingId) {

        return Optional.ofNullable(customerVehicleBookingRepository.findById(customerVehicleBookingId)
                .orElseThrow(() -> new RuntimeException("CustomerVehicleBooking not found with customerVehicleBookingId: " + customerVehicleBookingId)));
    }

    @Transactional
    @Override
    public boolean existsByBooking(String booking) {
        return customerVehicleBookingRepository.existsByBooking(booking);
    }

    @Transactional
    @Override
    public List<CustomerVehicleBooking> findAll() {
        return customerVehicleBookingRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<BigDecimal> sumCustomerVehicleTotalBookingValue(LocalUser localUser, CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            customerVehicleBookingSearchDTO.setCustomerId(customer.getCustomerId());
            return customerVehicleBookingCustomRepository.sumCustomerVehicleTotalBookingValue(customerVehicleBookingSearchDTO);
        });
    }

    @Transactional
    @Override
    public Page<CustomerVehicleBookingDTO> searchPage(LocalUser localUser, CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO, Pageable pageable) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            customerVehicleBookingSearchDTO.setCustomerId(customer.getCustomerId());
            return customerVehicleBookingCustomRepository.searchPage(customerVehicleBookingSearchDTO, pageable);
        }).orElseThrow(() -> new RuntimeException("Customer not found for email: " + localUser.getUsername()));
    }

    @Transactional
    @Override
    public Page<CustomerVehicleBookingDTO> customerVehicleSearchPage(LocalUser localUser, CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO, Pageable pageable) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            customerVehicleBookingSearchDTO.setCustomerId(customer.getCustomerId());
            return customerVehicleBookingCustomRepository.customerVehicleSearchPage(customerVehicleBookingSearchDTO, pageable);
        }).orElseThrow(() -> new RuntimeException("Customer not found for email: " + localUser.getUsername()));
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleBooking> save(CustomerVehicleBooking customerVehicleBooking) {

        customerVehicleBooking.setCreatedDate(Instant.now());
        customerVehicleBooking.setEnabled(Boolean.TRUE);

        return Optional.of(customerVehicleBookingRepository.save(customerVehicleBooking));
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleBooking> finalizeBooking(UUID customerVehicleBookingId, CustomerVehicleBooking customerVehicleBooking) {
        return findById(customerVehicleBookingId)
                .map(existingCustomerVehicleBooking -> {

                    if (customerVehicleBooking.getBookingStartKM() != null) {
                        existingCustomerVehicleBooking.setBookingStartKM(customerVehicleBooking.getBookingStartKM());
                    }

                    if (customerVehicleBooking.getBookingEndKM() != null) {
                        existingCustomerVehicleBooking.setBookingEndKM(customerVehicleBooking.getBookingEndKM());
                    }

                    existingCustomerVehicleBooking.setBookingDeliveryDate(LocalDateTime.now());
                    existingCustomerVehicleBooking.setModifiedDate(Instant.now());

                    return customerVehicleBookingRepository.save(existingCustomerVehicleBooking);
                });
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleBooking> update(UUID customerVehicleBookingId, CustomerVehicleBooking customerVehicleBooking) {
        return findById(customerVehicleBookingId)
                .map(existingCustomerVehicleBooking -> {

                    existingCustomerVehicleBooking.setEnabled(customerVehicleBooking.getEnabled());
                    existingCustomerVehicleBooking.setModifiedDate(Instant.now());

                    return customerVehicleBookingRepository.save(existingCustomerVehicleBooking);
                });
    }

    @Transactional
    @Override
    public void delete(UUID customerVehicleBookingId) {
        findById(customerVehicleBookingId).ifPresent(customerVehicleBookingRepository::delete);
    }
}