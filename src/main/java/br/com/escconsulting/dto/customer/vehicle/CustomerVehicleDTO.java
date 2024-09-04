package br.com.escconsulting.dto.customer.vehicle;

import br.com.escconsulting.dto.customer.CustomerDTO;
import br.com.escconsulting.dto.customer.vehicle.address.CustomerVehicleAddressDTO;
import br.com.escconsulting.dto.vehicle.VehicleDTO;
import br.com.escconsulting.dto.vehicle.color.VehicleColorDTO;
import br.com.escconsulting.dto.vehicle.fuel.type.VehicleFuelTypeDTO;
import br.com.escconsulting.dto.vehicle.model.VehicleModelDTO;
import br.com.escconsulting.dto.vehicle.transmission.VehicleTransmissionDTO;
import br.com.escconsulting.entity.CustomerVehicleAddress;
import br.com.escconsulting.entity.State;
import br.com.escconsulting.entity.enumeration.AdvertisementStatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVehicleDTO {

    private UUID customerVehicleId;
    private CustomerDTO customer;
    private VehicleDTO vehicle;
    private VehicleModelDTO vehicleModel;
    private VehicleColorDTO vehicleColor;
    private VehicleFuelTypeDTO vehicleFuelType;
    private VehicleTransmissionDTO vehicleTransmission;
    private List<CustomerVehicleAddressDTO> addresses;
    private String description;
    private String licensePlate;
    private String renavam;
    private State renavamState;
    private String chassis;
    private Integer yearOfManufacture;
    private Integer yearOfTheCar;
    private BigDecimal vehicleValue;
    private Integer mileageCreated;
    private BigDecimal dailyRate;
    private BigDecimal cleaningFee;
    private Boolean unlimitedMileage;
    private Boolean limitedMileage;
    private Integer limitedMileageIncluded;
    private BigDecimal limitedMileageValue;
    private Boolean deliverToAddress;
    private BigDecimal mileageFeeDelivery;
    private Boolean pickUpAtAddress;
    private BigDecimal mileageFeePickUp;
    private String code;
    private Boolean customerVehicleValidated;
    private AdvertisementStatus advertisementStatus;
    private Instant createdDate;
    private Instant modifiedDate;
    private Boolean enabled;
}