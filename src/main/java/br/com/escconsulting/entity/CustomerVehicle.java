package br.com.escconsulting.entity;

import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "vehicle_transmission_id", nullable = false)
    private VehicleTransmission vehicleTransmission;

    @OneToMany(mappedBy = "customerVehicle", cascade = CascadeType.ALL)
    private List<CustomerVehicleAddress> addresses;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "license_plate", nullable = false, unique = true, length = 10)
    private String licensePlate;

    @Column(name = "renavam", nullable = false, unique = true, length = 20)
    private String renavam;

    @Column(name = "chassis", nullable = false, unique = true, length = 20)
    private String chassis;

    @Column(name = "year_of_manufacture", nullable = false)
    private Integer yearOfManufacture;

    @Column(name = "year_of_the_car", nullable = false)
    private Integer yearOfTheCar;

    @Column(name = "daily_rate", nullable = false, precision = 13, scale = 2)
    private BigDecimal dailyRate;

    @Column(name = "cleaning_fee", nullable = false, precision = 13, scale = 2)
    private BigDecimal cleaningFee;

    @Column(name = "unlimited_mileage", nullable = false)
    private Boolean unlimitedMileage;

    @Column(name = "limited_mileage", nullable = false)
    private Boolean limitedMileage;

    @Column(name = "limited_mileage_included")
    private Integer limitedMileageIncluded;

    @Column(name = "limited_mileage_value", precision = 13, scale = 2)
    private BigDecimal limitedMileageValue;

    @Column(name = "deliver_to_address", nullable = false)
    private Boolean deliverToAddress;

    @Column(name = "pick_up_at_address", nullable = false)
    private Boolean pickUpAtAddress;

    @Column(name = "mileage_fee_delivery", precision = 13, scale = 2)
    private BigDecimal mileageFeeDelivery;
}