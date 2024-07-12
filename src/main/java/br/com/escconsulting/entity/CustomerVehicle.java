package br.com.escconsulting.entity;

import br.com.escconsulting.entity.enumeration.AdvertisementStatus;
import br.com.escconsulting.entity.generic.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
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
     * Estado do Renavam do veículo.
     */
    @ManyToOne
    @JoinColumn(name = "renavam_state_id", nullable = false)
    private State renavamState;

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
     * Valor do veículo.
     */
    @Column(name = "vehicle_value", nullable = false, precision = 13, scale = 2)
    private BigDecimal vehicleValue;

    /**
     * Quilometragem no momento da criação do registro.
     */
    @Column(name = "mileage_created", nullable = false)
    private Integer mileageCreated;

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
     * Taxa de quilometragem para entrega do veículo.
     */
    @Column(name = "mileage_fee_delivery", precision = 13, scale = 2)
    private BigDecimal mileageFeeDelivery;

    /**
     * Indica se o veículo será retirado no endereço do cliente.
     */
    @Column(name = "pick_up_at_address", nullable = false)
    private Boolean pickUpAtAddress;

    /**
     * Taxa de quilometragem para retirada do veículo.
     */
    @Column(name = "mileage_fee_pick_up", precision = 13, scale = 2)
    private BigDecimal mileageFeePickUp;

    /**
     * Código único do veículo.
     */
    @Column(name = "code", nullable = false, unique = true, length = 100)
    private String code;

    /**
     * Indica se o veículo do cliente foi validado.
     */
    @Column(name = "customer_vehicle_validated", nullable = false)
    private Boolean customerVehicleValidated;

    /**
     * Status do anúncio do veículo.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "advertisement_status", nullable = false,  length = 20)
    private AdvertisementStatus advertisementStatus;

    /**
     * Método de callback que é executado antes de persistir a entidade no banco de dados.
     * Define valores padrão para certos campos se eles não foram preenchidos.
     */
    @PrePersist
    protected void prePersist() {
        // Define a taxa diária de aluguel padrão se não for especificada
        if (this.getDailyRate() == null) {
            this.setDailyRate(new BigDecimal(50));
        }

        // Define a taxa de limpeza padrão se não for especificada
        if (this.getCleaningFee() == null) {
            this.setCleaningFee(new BigDecimal(30));
        }

        // Define a quilometragem ilimitada como verdadeira se não for especificada
        if (this.getUnlimitedMileage() == null) {
            this.setUnlimitedMileage(Boolean.TRUE);
        }

        // Define a quilometragem limitada como falsa se não for especificada
        if (this.getLimitedMileage() == null) {
            this.setLimitedMileage(Boolean.FALSE);
        }

        // Define a entrega no endereço do cliente como falsa se não for especificada
        if (this.getDeliverToAddress() == null) {
            this.setDeliverToAddress(Boolean.FALSE);
        }

        // Define a retirada no endereço do cliente como falsa se não for especificada
        if (this.getPickUpAtAddress() == null) {
            this.setPickUpAtAddress(Boolean.FALSE);
        }

        // Define o campo de validação do veículo do cliente como falso se não for especificado
        if (this.getCustomerVehicleValidated() == null) {
            this.setCustomerVehicleValidated(Boolean.FALSE);
        }

        // Define o status do anúncio como rascunho se não for especificado
        if (this.getAdvertisementStatus() == null) {
            this.setAdvertisementStatus(AdvertisementStatus.DRAFT);
        }

        // Define a data de criação se não for especificada
        if (this.getCreatedDate() == null) {
            this.setCreatedDate(Instant.now());
        }

        // Define a entidade como habilitada se não for especificada
        if (this.getEnabled() == null) {
            this.setEnabled(Boolean.TRUE);
        }
    }
}