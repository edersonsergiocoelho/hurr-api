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
@Table(name = "vehicle_color")
public class VehicleColor extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 4908502092506326361L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_color_id")
    private UUID vehicleColorId;

    @Column(name = "vehicle_color_name", nullable = false)
    private String vehicleColorName;
}