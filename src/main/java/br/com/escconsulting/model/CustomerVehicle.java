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
@Table(name = "customer_vehicle")
public class CustomerVehicle extends AbstractEntity implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 9066140529426733461L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_vehicle_id")
    private UUID customerVehicleId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "vehicle_model_id", nullable = false)
    private VehicleModel vehicleModel;

    @ManyToOne
    @JoinColumn(name = "vehicle_color_id", nullable = false)
    private VehicleColor vehicleColor;

    @ManyToOne
    @JoinColumn(name = "vehicle_fuel_type_id", nullable = false)
    private VehicleFuelType vehicleFuelType;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;

    @Column(name = "renavam", nullable = false, unique = true)
    private String renavam;

    @Column(name = "chassis", nullable = false, unique = true)
    private String chassis;

    @Column(name = "year_of_manufacture", nullable = false)
    private Integer yearOfManufacture;

    @Column(name = "year_of_the_car", nullable = false)
    private Integer yearOfTheCard;
}