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
@Table(name = "vehicle_model")
public class VehicleModel extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 8392930615811115090L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_model_id", nullable = false)
    private UUID vehicleModelId;

    @Column(name = "vehicle_model_name", nullable = false, length = 100, unique = true)
    private String vehicleModelName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_categories_id", nullable = false)
    private VehicleCategory vehicleCategory;

}