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
@Table(name = "vehicle_transmission")
public class VehicleTransmission extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -8098568882258805043L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_transmission_id")
    private UUID vehicleTransmissionId;

    @Column(name = "vehicle_transmission_name", length = 100, nullable = false, unique = true)
    private String vehicleTransmissionName;
}