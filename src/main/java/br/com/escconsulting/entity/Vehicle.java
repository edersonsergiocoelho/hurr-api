package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "vehicle")
public class Vehicle extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -8767474541587195520L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID vehicleId;

    @Column(name = "vehicle_name", nullable = false, length = 100, unique = true)
    private String vehicleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_brand_id", nullable = false)
    private VehicleBrand vehicleBrand;
}