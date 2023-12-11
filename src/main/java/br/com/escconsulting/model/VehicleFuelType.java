package br.com.escconsulting.model;

import br.com.escconsulting.model.generic.AbstractEntity;
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
@Table(name = "vehicle_fuel_type")
public class VehicleFuelType extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1870227821843440688L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_fuel_type_id")
    private UUID vehicleFuelTypeId;

    @Column(name = "vehicle_fuel_type_name", nullable = false, unique = true)
    private String vehicleFuelTypeName;
}