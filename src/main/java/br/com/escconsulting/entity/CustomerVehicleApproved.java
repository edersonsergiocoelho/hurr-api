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
@Table(name = "customer_vehicle_approved")
public class CustomerVehicleApproved extends AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_vehicle_approved_id")
    private UUID customerVehicleApprovedId;

    @ManyToOne
    @JoinColumn(name = "customer_vehicle_id", nullable = false)
    private CustomerVehicle customerVehicle;

    @Column(name = "approved_by")
    private UUID approvedBy;

    @Column(name = "reproved_by")
    private UUID reprovedBy;

    @Column(name = "message")
    private String message;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(name = "modified_by")
    private UUID modifiedBy;
}