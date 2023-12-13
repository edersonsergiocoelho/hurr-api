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
@Table(name = "vehicle_categories")
public class VehicleCategory extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 8432591261011933818L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_categories_id", nullable = false)
    private UUID vehicleCategoryId;

    @Column(name = "vehicle_categories_name", nullable = false, length = 100, unique = true)
    private String vehicleCategoryName;

}