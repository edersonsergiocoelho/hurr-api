package br.com.escconsulting.dto.customer.withdrawal.request;

import br.com.escconsulting.dto.customer.CustomerDTO;
import br.com.escconsulting.dto.customer.bank.account.CustomerBankAccountDTO;
import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingDTO;
import br.com.escconsulting.dto.payment.method.PaymentMethodDTO;
import br.com.escconsulting.dto.payment.status.PaymentStatusDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWithdrawalRequestDTO {

    private UUID customerWithdrawalRequestId;
    private CustomerDTO customer;
    private CustomerVehicleBookingDTO customerVehicleBooking;
    private CustomerBankAccountDTO customerBankAccount;
    private PaymentMethodDTO paymentMethod;
    private PaymentStatusDTO paymentStatus;
    private LocalDateTime withdrawalDate;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}