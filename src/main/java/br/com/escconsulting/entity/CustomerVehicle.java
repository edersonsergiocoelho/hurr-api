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
     * Serial version UID para serialização.
     */
    @Serial
    private static final long serialVersionUID = 9066140529426733461L;

    /**
     * Identificador único do veículo do cliente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_vehicle_id")
    private UUID customerVehicleId;

    /**
     * Cliente associado a este veículo.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Veículo associado a este registro.
     */
    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    /**
     * Modelo do veículo associado a este registro.
     */
    @ManyToOne
    @JoinColumn(name = "vehicle_model_id", nullable = false)
    private VehicleModel vehicleModel;

    /**
     * Cor do veículo associado a este registro.
     */
    @ManyToOne
    @JoinColumn(name = "vehicle_color_id", nullable = false)
    private VehicleColor vehicleColor;

    /**
     * Tipo de combustível do veículo associado a este registro.
     */
    @ManyToOne
    @JoinColumn(name = "vehicle_fuel_type_id", nullable = false)
    private VehicleFuelType vehicleFuelType;

    /**
     * Tipo de transmissão do veículo associado a este registro.
     */
    @ManyToOne
    @JoinColumn(name = "vehicle_transmission_id", nullable = false)
    private VehicleTransmission vehicleTransmission;

    /**
     * Lista de endereços associados a este veículo do cliente.
     */
    @OneToMany(mappedBy = "customerVehicle", cascade = CascadeType.ALL)
    private List<CustomerVehicleAddress> addresses;

    /**
     * Descrição do veículo.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Placa do veículo.
     */
    @Column(name = "license_plate", nullable = false, unique = true, length = 10)
    private String licensePlate;

    /**
     * Renavam do veículo.
     */
    @Column(name = "renavam", nullable = false, unique = true, length = 20)
    private String renavam;

    /**
     * Chassis do veículo.
     */
    @Column(name = "chassis", nullable = false, unique = true, length = 20)
    private String chassis;

    /**
     * Ano de fabricação do veículo.
     */
    @Column(name = "year_of_manufacture", nullable = false)
    private Integer yearOfManufacture;

    /**
     * Ano do veículo.
     */
    @Column(name = "year_of_the_car", nullable = false)
    private Integer yearOfTheCar;

    /**
     * Taxa diária de aluguel do veículo.
     */
    @Column(name = "daily_rate", nullable = false, precision = 13, scale = 2)
    private BigDecimal dailyRate;

    /**
     * Taxa de limpeza do veículo.
     */
    @Column(name = "cleaning_fee", nullable = false, precision = 13, scale = 2)
    private BigDecimal cleaningFee;

    /**
     * Indica se o veículo tem quilometragem ilimitada.
     */
    @Column(name = "unlimited_mileage", nullable = false)
    private Boolean unlimitedMileage;

    /**
     * Indica se o veículo tem quilometragem limitada.
     */
    @Column(name = "limited_mileage", nullable = false)
    private Boolean limitedMileage;

    /**
     * Quantidade de quilômetros incluídos na quilometragem limitada.
     */
    @Column(name = "limited_mileage_included")
    private Integer limitedMileageIncluded;

    /**
     * Valor da quilometragem limitada.
     */
    @Column(name = "limited_mileage_value", precision = 13, scale = 2)
    private BigDecimal limitedMileageValue;

    /**
     * Indica se o veículo será entregue no endereço do cliente.
     */
    @Column(name = "deliver_to_address", nullable = false)
    private Boolean deliverToAddress;

    /**
     * Indica se o veículo será retirado no endereço do cliente.
     */
    @Column(name = "pick_up_at_address", nullable = false)
    private Boolean pickUpAtAddress;

    /**
     * Taxa de quilometragem para entrega do veículo.
     */
    @Column(name = "mileage_fee_delivery", precision = 13, scale = 2)
    private BigDecimal mileageFeeDelivery;
}