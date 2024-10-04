package br.com.escconsulting.service.impl;

import br.com.escconsulting.dto.LocalUser;
import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingDTO;
import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingSearchDTO;
import br.com.escconsulting.dto.mercado.pago.MPPaymentDTO;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.CustomerVehicleBooking;
import br.com.escconsulting.mapper.CustomerVehicleBookingMapper;
import br.com.escconsulting.mapper.MPPaymentMapper;
import br.com.escconsulting.repository.CustomerVehicleBookingRepository;
import br.com.escconsulting.repository.custom.CustomerVehicleBookingCustomRepository;
import br.com.escconsulting.service.CustomerService;
import br.com.escconsulting.service.CustomerVehicleBookingService;
import br.com.escconsulting.service.mercado.pago.MPPaymentService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.payment.PaymentRefund;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerVehicleBookingServiceImpl implements CustomerVehicleBookingService {

    private final CustomerService customerService;

    private final CustomerVehicleBookingRepository customerVehicleBookingRepository;

    private final CustomerVehicleBookingCustomRepository customerVehicleBookingCustomRepository;

    private final MPPaymentService mpPaymentService;

    @Transactional
    @Override
    public Optional<CustomerVehicleBooking> findById(UUID customerVehicleBookingId) {

        return Optional.ofNullable(customerVehicleBookingCustomRepository.findById(customerVehicleBookingId)
                .orElseThrow(() -> new RuntimeException("CustomerVehicleBooking not found with customerVehicleBookingId: " + customerVehicleBookingId)));
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleBooking> findByPaymentId(Long paymentId) {

        return Optional.ofNullable(customerVehicleBookingCustomRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new RuntimeException("CustomerVehicleBooking not found with paymentId: " + paymentId)));
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
    public List<CustomerVehicleBooking> findByCustomerVehicleWithdrawableBalance(LocalUser localUser) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            return customerVehicleBookingCustomRepository.findByCustomerVehicleWithdrawableBalance(customer.getCustomerId());
        }).orElseGet(ArrayList::new);
    }

    @Transactional
    @Override
    public Optional<BigDecimal> sumCustomerVehicleTotalEarnings(LocalUser localUser, CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            customerVehicleBookingSearchDTO.setCustomerId(customer.getCustomerId());
            return customerVehicleBookingCustomRepository.sumCustomerVehicleTotalEarnings(customerVehicleBookingSearchDTO);
        });
    }

    @Transactional
    @Override
    public Optional<BigDecimal> sumCustomerVehicleWithdrawableCurrentBalance(LocalUser localUser, CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            customerVehicleBookingSearchDTO.setCustomerId(customer.getCustomerId());
            return customerVehicleBookingCustomRepository.sumCustomerVehicleWithdrawableCurrentBalance(customerVehicleBookingSearchDTO);
        });
    }

    @Transactional
    @Override
    public Optional<BigDecimal> sumCustomerVehicleWithdrawableBalance(LocalUser localUser, CustomerVehicleBookingSearchDTO customerVehicleBookingSearchDTO) {
        Optional<Customer> optionalCustomer = customerService.findByEmail(localUser.getUsername());

        return optionalCustomer.map(customer -> {
            customerVehicleBookingSearchDTO.setCustomerId(customer.getCustomerId());
            return customerVehicleBookingCustomRepository.withdrawableBalance(customerVehicleBookingSearchDTO);
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

        return Optional.of(customerVehicleBookingRepository.save(customerVehicleBooking));
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleBooking> checkIn(UUID customerVehicleBookingId, CustomerVehicleBooking customerVehicleBooking) {
        return findById(customerVehicleBookingId)
                .map(existingCustomerVehicleBooking -> {

                    CustomerVehicleBookingMapper.INSTANCE.update(customerVehicleBooking, existingCustomerVehicleBooking);

                    existingCustomerVehicleBooking.setBookingStartDate(LocalDateTime.now());
                    existingCustomerVehicleBooking.setModifiedDate(Instant.now());

                    return customerVehicleBookingRepository.save(existingCustomerVehicleBooking);
                });
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleBooking> checkOut(UUID customerVehicleBookingId, CustomerVehicleBooking customerVehicleBooking) {
        return findById(customerVehicleBookingId)
                .map(existingCustomerVehicleBooking -> {

                    if (customerVehicleBooking.getTotalBookingValue().longValue() > existingCustomerVehicleBooking.getTotalBookingValue().longValue()) {
                        try {
                            Optional<Payment> capture = mpPaymentService.capture(customerVehicleBooking.getMpPaymentId(), customerVehicleBooking.getTotalBookingValue());

                            if (capture.isPresent()) {
                                MPPaymentDTO MPPaymentDTOData = new MPPaymentDTO();

                                MPPaymentMapper.INSTANCE.update(capture.get(), MPPaymentDTOData);
                                customerVehicleBooking.setMPPaymentDTOData(MPPaymentDTOData);
                            }

                        } catch (MPException e) {
                            throw new RuntimeException(e);
                        } catch (MPApiException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    CustomerVehicleBookingMapper.INSTANCE.update(customerVehicleBooking, existingCustomerVehicleBooking);

                    existingCustomerVehicleBooking.setBookingEndDate(LocalDateTime.now());
                    existingCustomerVehicleBooking.setModifiedDate(Instant.now());

                    return customerVehicleBookingRepository.save(existingCustomerVehicleBooking);
                });
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleBooking> update(UUID customerVehicleBookingId, CustomerVehicleBooking customerVehicleBooking) {
        return findById(customerVehicleBookingId)
                .map(existingCustomerVehicleBooking -> {

                    CustomerVehicleBookingMapper.INSTANCE.update(customerVehicleBooking, existingCustomerVehicleBooking);

                    existingCustomerVehicleBooking.setModifiedDate(Instant.now());

                    return customerVehicleBookingRepository.save(existingCustomerVehicleBooking);
                });
    }

    @Transactional
    @Override
    public Optional<CustomerVehicleBooking> cancelBooking(UUID customerVehicleBookingId, CustomerVehicleBooking customerVehicleBooking) {
        return findById(customerVehicleBookingId)
                .map(existingCustomerVehicleBooking -> {
                    // Verifica se a reserva já foi cancelada
                    if (existingCustomerVehicleBooking.getBookingCancellationDate() != null) {
                        throw new IllegalStateException("customer.vehicle.booking.already.cancelled");
                    }

                    // Atualiza os dados de cancelamento
                    existingCustomerVehicleBooking.setBookingCancellationDate(LocalDateTime.now());
                    existingCustomerVehicleBooking.setModifiedDate(Instant.now());

                    // Salva a reserva atualizada
                    return customerVehicleBookingRepository.save(existingCustomerVehicleBooking);
                })
                .map(updatedCustomerVehicleBooking -> {
                    try {

                        // Junta a data de início da reserva com a hora de início
                        LocalDateTime reservationStartDateTime = LocalDateTime.of(
                                updatedCustomerVehicleBooking.getReservationStartDate(),
                                LocalTime.parse(updatedCustomerVehicleBooking.getReservationStartTime())
                        );

                        // Verifica se a data atual ultrapassa a data de início da reserva
                        if (LocalDateTime.now().isAfter(reservationStartDateTime)) {
                            // Aplica desconto de 10% no reembolso usando BigDecimal
                            BigDecimal originalAmount = updatedCustomerVehicleBooking.getTotalBookingValue();
                            BigDecimal discountRate = new BigDecimal("0.10"); // 10% de desconto
                            BigDecimal discountAmount = originalAmount.multiply(discountRate); // Calcula o valor do desconto
                            BigDecimal refundAmount = originalAmount.subtract(discountAmount); // Subtrai o desconto do valor original

                            Optional<PaymentRefund> refund = mpPaymentService.refund(
                                    updatedCustomerVehicleBooking.getMpPaymentId(),
                                    refundAmount
                            );

                            if (!refund.isPresent()) {
                                throw new IllegalStateException("customer.vehicle.booking.refund.failed");
                            }

                        } else {
                            // Reembolso integral se a data atual não ultrapassou a data de início da reserva
                            Optional<PaymentRefund> refund = mpPaymentService.refund(updatedCustomerVehicleBooking.getMpPaymentId());

                            if (!refund.isPresent()) {
                                throw new IllegalStateException("customer.vehicle.booking.refund.failed");
                            }
                        }

                    } catch (MPException | MPApiException e) {
                        throw new RuntimeException("customer.vehicle.booking.refund.error", e);
                    }

                    return updatedCustomerVehicleBooking; // Retorna a reserva cancelada após o processamento do reembolso
                });
    }

    @Transactional
    @Override
    public void delete(UUID customerVehicleBookingId) {
        findById(customerVehicleBookingId).ifPresent(customerVehicleBookingRepository::delete);
    }
}