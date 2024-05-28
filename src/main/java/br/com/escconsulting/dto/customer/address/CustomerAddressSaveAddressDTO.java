package br.com.escconsulting.dto.customer.address;

import br.com.escconsulting.entity.Address;
import br.com.escconsulting.entity.AddressType;
import br.com.escconsulting.entity.Customer;
import lombok.Getter;

import java.util.List;

@Getter
public class CustomerAddressSaveAddressDTO {

    Customer customer;

    Address address;

    List<AddressType> addressTypes;
}