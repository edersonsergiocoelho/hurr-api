package br.com.escconsulting.dto.customer.vehicle.review;

import br.com.escconsulting.dto.customer.CustomerDTO;
import br.com.escconsulting.dto.customer.vehicle.booking.CustomerVehicleBookingDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVehicleReviewDTO {

    private UUID customerVehicleReviewId;
    private CustomerVehicleBookingDTO customerVehicleBooking;
    private CustomerDTO customer;
    private String review;
    private Integer rating;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}