package br.com.escconsulting.dto.customer.vehicle.address;

import br.com.escconsulting.entity.Address;
import br.com.escconsulting.entity.AddressType;
import br.com.escconsulting.entity.Customer;
import br.com.escconsulting.entity.CustomerVehicle;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class CustomerVehicleAddressSaveAddressDTO {

    CustomerVehicle customerVehicle;

    Address address;

    List<AddressType> addressTypes;
}