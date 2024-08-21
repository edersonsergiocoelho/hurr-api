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
@Table(name = "vehicle_brand")
public class VehicleBrand extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -2354532169797707959L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID vehicleBrandId;

    @Column(name = "vehicle_brand_name", nullable = false, unique = true)
    private String vehicleBrandName;

    @Column(name = "file_id")
    private UUID fileId;
}