package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "customer_vehicle_review")
public class CustomerVehicleReview extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -3033490461178270354L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID customerVehicleReviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_vehicle_booking_id", nullable = false)
    private CustomerVehicleBooking customerVehicleBooking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(columnDefinition = "text", nullable = false)
    private String review;

    @Column(nullable = false)
    private Integer rating;

    @PrePersist
    protected void prePersist() {
        if (this.getCreatedDate() == null) {
            this.setCreatedDate(Instant.now());
        }

        if (this.getEnabled() == null) {
            this.setEnabled(Boolean.TRUE);
        }
    }
}